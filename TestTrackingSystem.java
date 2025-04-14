public class TestTrackingSystem {
    public static void main(String[] args) {
        // TEMP HELPER and TESTER FILE
        // Create TrackingSystem
        TrackingSystem trackingSystem = new TrackingSystem();

        // Load data from your CSV file
        trackingSystem.loadData("rso_metrics.csv");

        // Get all the loaded space objects
        for (SpaceObject obj : trackingSystem.getSpaceObjects()) {
            obj.displayInfo();
            System.out.println("-------------------------");
        }
    }
}
