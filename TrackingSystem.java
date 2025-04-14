import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The TrackingSystem class is responsible for reading debris and satellite data from a CSV file
 * and storing it into a list of SpaceObjects (Debris or Satellite).
 * <p>
 * This class is used by MissionControl to perform operations on space objects.
 * </p>
 * 
 * @author Caitlin Gregory
 * @author Daniela Gutierrez
 */

public class TrackingSystem {
    
    /**
     * A list to store all the loaded SpaceObject instances (Debris or Satellite).
     */
    private List<SpaceObject> spaceObjects;

    /**
     * Default constructor initializes an empty list of space objects.
     * 
     * Chose a list because:
     * a) Add element at end -> O(1)
     * b) Access element by index -> O(1)
     * c) Search element (linear scan) -> O(n)
     * d) Remove by index -> O(n)
     * Fast for adding. Fast for going through all elements
     * 
     * Hashmap would be better if I need to search for a SPECIFIC object (like by Record ID or Satellite Name)
     */

    public TrackingSystem() {
        spaceObjects = new ArrayList<>();
    }

    /**
     * Loads data from a CSV file into the spaceObjects list.
     * Parses each line into a Debris or Satellite object based on object type.
     * Handles missing numeric values safely by setting default values (0 for int/long, 0.0 for double).
     * 
     * @param filePath The path to the CSV file containing space object data.
     */
    public void loadData(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                // Split the CSV line into fields
                String[] fields = line.split(",");

                // Extract each field carefully and parse data types (adjust based on your CSV)
                String recordId = fields[0];
                String satelliteName = fields[2];
                String country = fields[3];
                String orbitType = fields[4];
                String objectType = fields[5];
                int launchYear = fields[6].isEmpty() ? 0 : Integer.parseInt(fields[6]);
                String launchSite = fields[7];
                double longitude = fields[8].isEmpty() ? 0.0 : Double.parseDouble(fields[8]);
                double avgLongitude = fields[9].isEmpty() ? 0.0 : Double.parseDouble(fields[9]);
                String geohash = fields[10];
                int daysOld = fields[19].isEmpty() ? 0 : Integer.parseInt(fields[19]);
                long conjunctionCount = fields[20].isEmpty() ? 0L : Long.parseLong(fields[20]);

                // Decide which type of object to create based on objectType
                SpaceObject obj;

                if (objectType.equalsIgnoreCase("Debris")) {
                    // Create a Debris object
                    obj = new Debris(recordId, satelliteName, country, orbitType,
                                     launchYear, launchSite, longitude, avgLongitude,
                                     geohash, daysOld, conjunctionCount);
                } else {
                    // Create a Satellite object
                    obj = new Satellite(recordId, satelliteName, country, orbitType,
                                        launchYear, launchSite, longitude, avgLongitude,
                                        geohash, daysOld);
                }
                // Add the object to the spaceObjects list
                spaceObjects.add(obj);
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Returns the list of loaded space objects (Debris and Satellite).
     * 
     * @return A list of SpaceObject instances.
     */
    public List<SpaceObject> getSpaceObjects() {
        return spaceObjects;
    }
}
