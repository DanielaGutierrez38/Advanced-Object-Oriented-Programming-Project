import java.util.Scanner;

class MissionControl {
    
    protected TrackingSystem trackingSystem;
    private User currentUser;

    public MissionControl() {
        this.trackingSystem = new TrackingSystem("rso_metrics.csv");
    }

    public void handleUserMenu() {}

    // ✅ Accept 'choice' passed in from DisplayMenu
    public void trackObjectsInSpace(int choice) {
        Logger logger = new Logger(); // Log start of query

        // Log query type
        switch (choice) {
            case 1:
                logger.logQueryRocketBody();
                break;
            case 2:
                logger.logQueryDebris();
                break;
            case 3:
                logger.logQueryPayload();
                break;
            case 4:
                logger.logQueryUnknown();
                break;
            default:
                System.out.println("Invalid input");
                return;
        }

        // Print matching space objects
        for (SpaceObject obj : trackingSystem.getAllObjects().values()) {
            boolean match = false;

            switch (choice) {
                case 1:
                    if (obj instanceof RocketBody) match = true;
                    break;
                case 2:
                    if (obj instanceof Debris) match = true;
                    break;
                case 3:
                    if (obj instanceof Payload) match = true;
                    break;
                case 4:
                    if (obj instanceof UnknownObject) match = true;
                    break;
            }

            if (match) {
                obj.displayInfo();
                System.out.println("--------------------------------------------------");
            }
        }
    }

    public void assessOrbitStatus() {}
}
