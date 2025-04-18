import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Arrays;

/**
 * Builds a dictionary of SpaceObject instances from a 2D array.
 * This includes Debris, Satellite, RocketBody, Payload, and UnknownObject.
 * <p>
 * The dictionary is keyed by the unique record ID for each space object.
 * </p>
 * 
 * @author Caitlin Gregory
 * @author Daniela Gutierrez
 */
public class DictionaryBuilder {

    /**
     * Parses the 2D array and constructs appropriate SpaceObject subclasses
     * based on the objectType field. Returns a map of recordId → SpaceObject.
     *
     * @param data 2D array of CSV data rows split into fields
     * @return Map of SpaceObject instances keyed by record ID
     */
    public static Map<String, SpaceObject> buildFromArray(String[][] data) {
        Map<String, SpaceObject> spaceMap = new LinkedHashMap<>();

        for (String[] fields : data) {
            try {
                // Common field parsing
                String recordId = fields[0];
                String satelliteName = fields[2];
                String country = fields[3];
                String orbitType = fields[4];
                String objectType = fields[5];
                int launchYear = fields[6].isEmpty() ? 0 : Integer.parseInt(fields[6]);
                String launchSite = fields[7];
                double longitude = fields[8].isEmpty() ? 0.0 : Double.parseDouble(fields[8]);
                double avgLongitude = fields[9].isEmpty() ? 0.0 : Double.parseDouble(fields[9]);
                String geohash = fields[10] + ", " + fields[11];

                // Optional/defensive field parsing
                String hrrCategory = (fields.length > 12 && !fields[12].isEmpty()) ? fields[12] : "N/A";
                boolean isNominated = (fields.length > 13 && fields[13].equalsIgnoreCase("true"));
                boolean hasDossier = (fields.length > 15 && fields[15].equalsIgnoreCase("true"));
                boolean isUnknownObject = (fields.length > 17 && fields[17].equalsIgnoreCase("true"));
                int daysOld = (fields.length > 19 && !fields[19].isEmpty() && fields[19].matches("\\d+"))
                                ? Integer.parseInt(fields[19]) : 0;

                // Instantiate the appropriate subclass based on objectType
                SpaceObject obj;

                switch (objectType.toLowerCase()) {
                    case "debris":
                        obj = new Debris(recordId, satelliteName, country, orbitType, launchYear,
                                launchSite, longitude, avgLongitude, geohash, hrrCategory,
                                isNominated, hasDossier, isUnknownObject, daysOld);
                        break;
                    case "satellite":
                        obj = new Satellite(recordId, satelliteName, country, orbitType, launchYear,
                                launchSite, longitude, avgLongitude, geohash, hrrCategory,
                                isNominated, hasDossier, isUnknownObject, daysOld);
                        break;
                    case "rocket body":
                        obj = new RocketBody(recordId, satelliteName, country, orbitType, launchYear,
                                launchSite, longitude, avgLongitude, geohash, hrrCategory,
                                isNominated, hasDossier, isUnknownObject, daysOld);
                        break;
                    case "payload":
                        obj = new Payload(recordId, satelliteName, country, orbitType, launchYear,
                                launchSite, longitude, avgLongitude, geohash, hrrCategory,
                                isNominated, hasDossier, isUnknownObject, daysOld);
                        break;
                    case "unknown":
                        obj = new UnknownObject(recordId, satelliteName, country, orbitType, launchYear,
                                launchSite, longitude, avgLongitude, geohash, hrrCategory,
                                isNominated, hasDossier, isUnknownObject, daysOld);
                        break;
                    default:
                        System.out.println("Unknown object type: " + objectType + " for record: " + recordId);
                        continue;  // Skip unknown types
                }

                spaceMap.put(recordId, obj);  // Store parsed object

            } catch (Exception e) {
                System.out.println("Error parsing row [" + Arrays.toString(fields) + "]: " + e.getMessage());
            }
        }

        return spaceMap;
    }
}
