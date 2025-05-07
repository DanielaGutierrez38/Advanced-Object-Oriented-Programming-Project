import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.*;

/**
 * JUnit test class for verifying the functionality of the MissionControl class.
 * 
 * It includes tests for:
 * - Analyzing long-term impact of space debris in LEO with specified constraints.
 * - Generating a density report for space objects within a specified longitude range.
 * 
 * These tests redirect standard output to validate printed content from the simulation.
 * 
 * @author Caitlin Gregory
 */
class MissionControlTest {

    private MissionControl missionControl;
    private TrackingSystem trackingSystem;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /**
     * Sets up the test environment before each test case.
     * Redirects system output and initializes the test objects.
     */
    @BeforeEach
    void setUp() {
        // Redirect output to capture printed results
        System.setOut(new PrintStream(outContent));

        // Initialize test TrackingSystem and MissionControl
        trackingSystem = new TrackingSystem();
        trackingSystem.setObjects(new HashMap<>());
        missionControl = new MissionControl(trackingSystem);

        // Add sample space objects to simulate filtering
        trackingSystem.getAllObjects().put("qualifying", new Debris("9999", "TestSat", "US", "LEO", 2022, "VAFB",
                45.0, 45.0, "geo", "HRR", false, false, false, 300, 5));
        trackingSystem.getAllObjects().put("nonLEO", new Debris("9998", "NonLEO", "US", "GEO", 2022, "VAFB",
                45.0, 45.0, "geo", "HRR", false, false, false, 300, 5));
        trackingSystem.getAllObjects().put("tooYoung", new Debris("9997", "Young", "US", "LEO", 2022, "VAFB",
                45.0, 45.0, "geo", "HRR", false, false, false, 100, 5));
        trackingSystem.getAllObjects().put("noConj", new Debris("9996", "NoConj", "US", "LEO", 2022, "VAFB",
                45.0, 45.0, "geo", "HRR", false, false, false, 300, 0));
    }

    /**
     * Cleans up after each test by restoring the original system output
     * and resetting the output buffer.
     */
    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        outContent.reset();
    }

    /**
     * Tests that the `analyzeLongTermImpact()` method only prints objects that:
     * - Are in LEO
     * - Are older than 200 days
     * - Have conjunction count > 0
     * 
     * Validates output content by checking presence or absence of record IDs.
     */
    @Test
    void testAnalyzeLongTermImpact() {
        outContent.reset(); // Clear output buffer

        missionControl.analyzeLongTermImpact();

        String output = outContent.toString();

        assertTrue(output.contains("Record ID: 9999"), "Should include qualifying object");
        assertFalse(output.contains("9998"), "Should exclude non-LEO object");
        assertFalse(output.contains("9997"), "Should exclude young object");
        assertFalse(output.contains("9996"), "Should exclude object with 0 conjunctions");
    }

    /**
     * Tests that the `generateDensityReport()` method only prints objects that
     * fall within the specified longitude range.
     * 
     * Checks output to verify correct inclusion and exclusion of object IDs.
     */
    @Test
    void testGenerateDensityReport() {
        outContent.reset();

        // Add sample debris objects for testing range filtering
        trackingSystem.getAllObjects().put("inRange", new Debris("123", "InRange", "US", "LEO", 2021, "CCAFS",
                100.0, 90.0, "abc", "HRR", false, false, false, 100, 1));
        trackingSystem.getAllObjects().put("outRange", new Debris("124", "OutRange", "US", "LEO", 2021, "CCAFS",
                1000.0, 90.0, "abc", "HRR", false, false, false, 100, 1));

        missionControl.generateDensityReport(10, 200);

        String output = outContent.toString();
        assertTrue(output.contains("Record ID: 123"), "Should include object in range");
        assertFalse(output.contains("Record ID: 124"), "Should not include object out of range");
    }
    /**
     * Tests that the `trackObjectsInLEO()` method only prints objects 
     * that have "LEO" as their orbit type.
     * 
     * This method sets up one qualifying (LEO) object and one non-LEO object.
     * It then verifies that only the LEO object appears in the output.
     */
    @Test
    void testTrackObjectsInLEO() {
        this.outContent.reset();

        // Add one LEO and one non-LEO object
        this.trackingSystem.getAllObjects().put("leo1", new Debris("2001", "LEO-Obj1", "US", "LEO", 2020, "KSC",
                10.0, 9.0, "g1", "HRR", false, false, false, 100, 2));
        this.trackingSystem.getAllObjects().put("geo1", new Debris("2002", "GEO-Obj1", "US", "GEO", 2020, "KSC",
                10.0, 9.0, "g2", "HRR", false, false, false, 100, 2));

        // Invoke the method
        this.missionControl.trackObjectsInLEO();

        // Capture and test output
        String output = this.outContent.toString();

        Assertions.assertTrue(output.contains("LEO-Obj1"), "Should display LEO object");
        Assertions.assertFalse(output.contains("GEO-Obj1"), "Should not display non-LEO object");
    }
}
