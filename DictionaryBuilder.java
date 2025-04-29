import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Arrays;

/**
 * Builds a dictionary of SpaceObject instances from a 2D array.
 * Includes Debris, Satellite, RocketBody, Payload, and UnknownObject.
 * Keyed by record ID.
 * 
 * @author Caitlin Gregory
 * @author Daniela Gutierrez
 */
public class DictionaryBuilder{

    /** Default constructor */
    DictionaryBuilder() {}

    /**
     * Parses the 2D array and constructs appropriate SpaceObject subclasses.
     *
     * @param data 2D array of CSV data rows split into fields
     * @param columnIndex mapping from column names to their index in the row
     * @return Map of SpaceObject instances keyed by record ID
     */
    public static Map<String, SpaceObject> buildFromArray(String[][] data, Map<String, Integer> columnIndex){
        Map<String, SpaceObject> spaceMap = new LinkedHashMap<>();

        for (String[] fields : data){
            try{
                // Mandatory fields
                String recordId = getSafeField(fields, columnIndex, "record_id", "unknown");
                String satelliteName = getSafeField(fields, columnIndex, "satellite_name", "unknown");
                String country = getSafeField(fields, columnIndex, "country", "unknown");
                String orbitType = getSafeField(fields, columnIndex, "approximate_orbit_type", "unknown");
                String objectType = getSafeField(fields, columnIndex, "object_type", "unknown");
                int launchYear = parseIntSafe(getSafeField(fields, columnIndex, "launch_year", "0"));
                String launchSite = getSafeField(fields, columnIndex, "launch_site", "unknown");
                double longitude = parseDoubleSafe(getSafeField(fields, columnIndex, "longitude", "0.0"));
                double avgLongitude = parseDoubleSafe(getSafeField(fields, columnIndex, "avg_longitude", "0.0"));
                String geohash = getSafeField(fields, columnIndex, "geohash", "unknown");

                // Optional fields (if they exist)
                String hrrCategory = getSafeField(fields, columnIndex, "hrr_category", "N/A");
                boolean isNominated = parseBooleanSafe(fields, columnIndex, "is_nominated");
                boolean hasDossier = parseBooleanSafe(fields, columnIndex, "has_dossier");
                boolean isUnknownObject = parseBooleanSafe(fields, columnIndex, "is_unk_object");
                int daysOld = parseIntSafe(getSafeField(fields, columnIndex, "days_old", "0"));
                int conjunctionCount = parseIntSafe(getSafeField(fields, columnIndex, "conjunction_count", "0"));

                // Instantiate correct SpaceObject subclass
                SpaceObject obj;
                switch (objectType.toLowerCase()){
                    case "debris":
                        obj = new Debris(recordId, satelliteName, country, orbitType, launchYear,
                                launchSite, longitude, avgLongitude, geohash, hrrCategory,
                                isNominated, hasDossier, isUnknownObject, daysOld, conjunctionCount);
                        break;
                    case "satellite":
                        obj = new Satellite(recordId, satelliteName, country, orbitType, launchYear,
                                launchSite, longitude, avgLongitude, geohash, hrrCategory,
                                isNominated, hasDossier, isUnknownObject, daysOld, conjunctionCount);
                        break;
                    case "rocket body":
                        obj = new RocketBody(recordId, satelliteName, country, orbitType, launchYear,
                                launchSite, longitude, avgLongitude, geohash, hrrCategory,
                                isNominated, hasDossier, isUnknownObject, daysOld, conjunctionCount);
                        break;
                    case "payload":
                        obj = new Payload(recordId, satelliteName, country, orbitType, launchYear,
                                launchSite, longitude, avgLongitude, geohash, hrrCategory,
                                isNominated, hasDossier, isUnknownObject, daysOld, conjunctionCount);
                        break;
                    case "unknown":
                        obj = new UnknownObject(recordId, satelliteName, country, orbitType, launchYear,
                                launchSite, longitude, avgLongitude, geohash, hrrCategory,
                                isNominated, hasDossier, isUnknownObject, daysOld, conjunctionCount);
                        break;
                    default:
                        System.out.println("Unknown object type: " + objectType + " for record: " + recordId);
                        continue;  // Skip unknown types
                }

                spaceMap.put(recordId, obj);

            } catch (Exception e){
                System.out.println("Error parsing row [" + Arrays.toString(fields) + "]: " + e.getMessage());
            }
        }
        return spaceMap;
    }

    /** Safe Integer parsing */
    private static int parseIntSafe(String value){
        if (value == null || value.isEmpty()) return 0;
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e){
            return 0;
        }
    }

    /** Safe Double parsing */
    private static double parseDoubleSafe(String value){
        if (value == null || value.isEmpty()) return 0.0;
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e){
            return 0.0;
        }
    }

    /** Super-safe field getter */
    private static String getSafeField(String[] fields, Map<String, Integer> map, String key, String defaultVal){
        if (!map.containsKey(key)){
            return defaultVal;
        }
        Integer index = map.get(key);
        if (index == null || index < 0 || index >= fields.length){
            return defaultVal;
        }
        return fields[index];
    }

    /** Super-safe Boolean parser */
    private static boolean parseBooleanSafe(String[] fields, Map<String, Integer> map, String key) {
        String value = getSafeField(fields, map, key, "false");
        return value.equalsIgnoreCase("true");
    }
}
