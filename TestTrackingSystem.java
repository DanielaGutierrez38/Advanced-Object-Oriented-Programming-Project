public class TestTrackingSystem {
    public static void main(String[] args) {
        // Create TrackingSystem and load data from CSV
        TrackingSystem trackingSystem = new TrackingSystem("rso_metrics.csv");

        // Iterate and print all loaded space objects
        for (SpaceObject obj : trackingSystem.getAllObjects().values()) {
            obj.displayInfo();  // Assumes you have a displayInfo() method
            System.out.println("-------------------------");
        }
    }
}
