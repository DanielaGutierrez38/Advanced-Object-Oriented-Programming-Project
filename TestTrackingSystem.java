/**
 * Class to test the functionality of TrackingSystem
 * @author Caitlin Gregory
 * @author Daniela Gutierrez
 */
public class TestTrackingSystem {

    /**Default Constructor */
    TestTrackingSystem(){}

    /**Main method of the tester
     * @param args main method thing
     */
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
