import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.*;

/**
 * JUnit test class for verifying the functionality of the MissionControl system,
 * including space object filtering, report generation, and user authentication/management.
 * 
 * This test suite validates:
 * <ul>
 *   <li>Analysis of long-term impact for qualifying debris</li>
 *   <li>Density report generation with longitude filtering</li>
 *   <li>Filtering of LEO objects for display</li>
 *   <li>User authentication across different scenarios</li>
 *   <li>User updates using flexible Administrator logic</li>
 * </ul>
 * 
 * This suite also uses a test-specific CSV file for user data to avoid interfering
 * with the main system’s persistent user data.
 * 
 * @author Caitlin Gregory
 * @author Daniela Gutierrez
 */
class MissionControlTest {

    private MissionControl missionControl;
    private TrackingSystem trackingSystem;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private UserManager userManager;

    /**
     * Prepares the test environment before each test.
     * Initializes test user manager, tracking system, and test debris data.
     */
    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));

        trackingSystem = new TrackingSystem();
        trackingSystem.setObjects(new HashMap<>());
        missionControl = new MissionControl(trackingSystem);

        userManager = new UserManager("test_users.csv");
        userManager.getAllUsers().clear();

        String salt = userManager.generateSalt();
        String hash = userManager.hashPassword("password123", salt);
        User user = new Scientist("testUser", hash, salt);
        userManager.addUser(user);

        trackingSystem.getAllObjects().put("qualifying", new Debris("9999", "TestSat", "US", "LEO", 2022, "VAFB",
                45.0, 45.0, "geo", "HRR", false, false, false, 300, 5));
    }

    /**
     * Cleans up after each test by resetting output and removing test files.
     */
    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        outContent.reset();
        new File("test_users.csv").delete();
    }

    /**
     * Tests long-term impact analysis by ensuring only qualifying debris is printed.
     */
    @Test
    void testAnalyzeLongTermImpact() {
        outContent.reset();
        missionControl.analyzeLongTermImpact();
        String output = outContent.toString();
        assertTrue(output.contains("Record ID: 9999"));
    }

    /**
     * Tests generation of a debris density report within a specified longitude range.
     * Verifies that only objects in range are written to the report file.
     * Uses a backup file to avoid overwriting actual production data.
     */
    @Test
    void testGenerateDensityReport() throws IOException {
        File realFile = new File("density_report.csv");
        File backupFile = new File("density_report_backup.csv");

        if (realFile.exists()) {
            if (backupFile.exists()) backupFile.delete();
            realFile.renameTo(backupFile);
        }

        try {
            if (realFile.exists()) realFile.delete();

            trackingSystem.getAllObjects().put("A", new Debris("A", "Debris-A", "US", "LEO", 2020, "KSC",
                20.0, 0.0, "hash1", "HRR", false, false, false, 100, 1));
            trackingSystem.getAllObjects().put("B", new Debris("B", "Debris-B", "US", "LEO", 2020, "KSC",
                150.0, 0.0, "hash2", "HRR", false, false, false, 100, 1));
            trackingSystem.getAllObjects().put("C", new Debris("C", "Debris-C", "US", "LEO", 2020, "KSC",
                500.0, 0.0, "hash3", "HRR", false, false, false, 100, 1)); //out of range

            missionControl.generateDensityReport(10, 200);

            assertTrue(realFile.exists(), "density_report.csv should be created");

            List<String> lines = java.nio.file.Files.readAllLines(realFile.toPath());
            assertTrue(lines.stream().anyMatch(line -> line.contains("Debris-A")), "Should contain Debris-A");
            assertTrue(lines.stream().anyMatch(line -> line.contains("Debris-B")), "Should contain Debris-B");
            assertFalse(lines.stream().anyMatch(line -> line.contains("Debris-C")), "Should NOT contain Debris-C");

        } finally {
            if (realFile.exists()) realFile.delete();
            if (backupFile.exists()) backupFile.renameTo(realFile);
        }
    }

    /**
     * Tests that only LEO objects are printed by trackObjectsInLEO().
     */
    @Test
    void testTrackObjectsInLEO() {
        outContent.reset();
        trackingSystem.getAllObjects().put("leo1", new Debris("2001", "LEO-Obj1", "US", "LEO", 2020, "KSC",
                10.0, 9.0, "g1", "HRR", false, false, false, 100, 2));
        trackingSystem.getAllObjects().put("geo1", new Debris("2002", "GEO-Obj1", "US", "GEO", 2020, "KSC",
                10.0, 9.0, "g2", "HRR", false, false, false, 100, 2));
        missionControl.trackObjectsInLEO();
        String output = outContent.toString();
        assertTrue(output.contains("LEO-Obj1"));
        assertFalse(output.contains("GEO-Obj1"));
    }

    /**
     * Tests user login with various valid and invalid combinations of username, password, and role.
     */
    @Test
    void testLogin() {
        boolean success = userManager.authenticateUser("testUser", "password123", "Scientist");
        assertTrue(success, "Login should succeed with correct credentials and role");

        success = userManager.authenticateUser("testUser", "wrongPass", "Scientist");
        assertFalse(success, "Login should fail with incorrect password");

        success = userManager.authenticateUser("testUser", "password123", "Administrator");
        assertFalse(success, "Login should fail with incorrect role");

        success = userManager.authenticateUser("ghost", "password123", "Scientist");
        assertFalse(success, "Login should fail for non-existent user");
    }

    /**
     * Tests Administrator's ability to update a user’s username and/or password.
     * Verifies that the user is correctly updated and remains valid after the change.
     */
    @Test
    void testUpdateUserFlexible() {
        UserManager mockManager = new UserManager("test_users.csv");
        Administrator admin = new Administrator("admin", "hash", "salt", mockManager);

        admin.createUser("oldUser", "oldPass", "Scientist");

        admin.updateUserFlexible("oldUser", "newUser", "[KEEP_OLD_PASSWORD]");
        User updated = mockManager.getUser("newUser");
        assertNotNull(updated, "Updated user should exist with new username");
        assertEquals("Scientist", updated.getUserType(), "User type should remain Scientist");

        admin.updateUserFlexible("newUser", "newUser", "newPass123");
        boolean success = mockManager.authenticateUser("newUser", "newPass123", "Scientist");
        assertTrue(success, "User should authenticate with new password");

        admin.updateUserFlexible("newUser", "finalUser", "finalPass");
        User finalUser = mockManager.getUser("finalUser");
        assertNotNull(finalUser, "Final user should exist after full update");
        assertTrue(mockManager.authenticateUser("finalUser", "finalPass", "Scientist"), "Should authenticate with final credentials");
    }
    
}
