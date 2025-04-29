import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

class MissionControlTest {

    private MissionControl missionControl;
    private TrackingSystem trackingSystem;

    @BeforeEach //set up objects
    void setUp() {
        trackingSystem = new TrackingSystem(); 
        trackingSystem.setObjects(new HashMap<>()); 

        missionControl = new MissionControl(trackingSystem);

        //Low Risk debris (orbital drift = 3)
        Debris lowRiskDebris = new Debris();
        lowRiskDebris.orbitType = "LEO";
        lowRiskDebris.longitude = 103;
        lowRiskDebris.avgLongitude = 100;
        lowRiskDebris.daysOld = 1000;
        lowRiskDebris.conjunctionCount = 2;
        trackingSystem.getAllObjects().put("lowRisk", lowRiskDebris);

        //Moderate Risk debris (orbital drift = 15)
        Debris moderateRiskDebris = new Debris();
        moderateRiskDebris.orbitType = "HEO";
        moderateRiskDebris.longitude = 115;
        moderateRiskDebris.avgLongitude = 100;
        moderateRiskDebris.daysOld = 800;
        moderateRiskDebris.conjunctionCount = 3;
        trackingSystem.getAllObjects().put("moderateRisk", moderateRiskDebris);

        //High Risk debris (orbital drift = 80)
        Debris highRiskDebris = new Debris();
        highRiskDebris.orbitType = "GEO";
        highRiskDebris.longitude = 180;
        highRiskDebris.avgLongitude = 100;
        highRiskDebris.daysOld = 500;
        highRiskDebris.conjunctionCount = 5;
        trackingSystem.getAllObjects().put("highRisk", highRiskDebris);

        //Exited debris (not in orbit)
        Debris exitedDebris = new Debris();
        exitedDebris.orbitType = "Unknown Orbit Category"; 
        exitedDebris.longitude = 100;
        exitedDebris.avgLongitude = 90;
        exitedDebris.daysOld = 1000;
        exitedDebris.conjunctionCount = 0;
        trackingSystem.getAllObjects().put("exitedDebris", exitedDebris);
    }

    @AfterEach //tear down objects after tests
    void tearDown() {
        missionControl = null;
        trackingSystem = null;
    }

    //testing low, moderate and highr risk debris that is still in orbit
    @Test
    void testLowRiskDebris() {
        missionControl.assessDebrisStillInOrbit();
        Debris lowRisk = (Debris) trackingSystem.getAllObjects().get("lowRisk");

        //assertions and message if the assertion fails
        assertTrue(lowRisk.isStillInOrbit(), "LowRisk debris should be still in orbit");
        assertEquals("Low Risk", lowRisk.getRiskLevel(), "LowRisk debris should be Low Risk");
    }

    @Test
    void testModerateRiskDebris() {
        missionControl.assessDebrisStillInOrbit();
        Debris moderateRisk = (Debris) trackingSystem.getAllObjects().get("moderateRisk");

        assertTrue(moderateRisk.isStillInOrbit(), "ModerateRisk debris should be still in orbit");
        assertEquals("Moderate Risk", moderateRisk.getRiskLevel(), "ModerateRisk debris should be Moderate Risk");
    }

    @Test
    void testHighRiskDebris() {
        missionControl.assessDebrisStillInOrbit();
        Debris highRisk = (Debris) trackingSystem.getAllObjects().get("highRisk");

        assertTrue(highRisk.isStillInOrbit(), "HighRisk debris should be still in orbit");
        assertEquals("High Risk", highRisk.getRiskLevel(), "HighRisk debris should be High Risk");
    }

    //testing not still in orbit debris
    @Test
    void testDebrisNotStillInOrbit() {
        missionControl.assessDebrisStillInOrbit();
        Debris fetchedDebris = (Debris) trackingSystem.getAllObjects().get("exitedDebris");

        assertFalse(fetchedDebris.isStillInOrbit(), "Debris should NOT be still in orbit");
        assertEquals("Low Risk", fetchedDebris.getRiskLevel(), "Risk level should be Low Risk");
    }
}
