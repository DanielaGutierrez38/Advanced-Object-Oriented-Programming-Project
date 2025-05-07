package com.spaceweb.model;
import java.util.*;

/**
 * Builds a dictionary of SpaceObject instances from a 2D array.
 * Supports Debris, Satellite, RocketBody, Payload, and UnknownObject.
 * 
 * @author Caitlin Gregory
 * @author Daniela Gutierrez
 */
public class DictionaryBuilder {
    /**
     * Converts CSV lines into a dictionary of SpaceObjects.
     * 
     * @param data the raw CSV lines (excluding the header)
     * @param columnIndex ßthe map of header column names to their indices
     * @return a map of SpaceObjects indexed by record ID
     */
    public static Map<String, SpaceObject> toArrayAndForward(String[][] data, Map<String, Integer> columnIndex) {
        Map<String, SpaceObject> objectMap = new LinkedHashMap<>();

        for (String[] fields : data) {
            if (fields.length < columnIndex.size()) {
                System.out.println("[WARNING] Skipping misaligned row: " + Arrays.toString(fields));
                continue;
            }

            try {
                String id = fields[columnIndex.get("record_id")];
                String orbit = fields[columnIndex.get("approximate_orbit_type")];
                String objType = fields[columnIndex.get("object_type")];
                String name = fields[columnIndex.get("satellite_name")];
                String country = fields[columnIndex.get("country")];
                String launchSite = fields[columnIndex.get("launch_site")];
                int launchYear = parseIntSafe(fields[columnIndex.get("launch_year")]);
                double longitude = parseDoubleSafe(fields[columnIndex.get("longitude")]);
                double avgLongitude = parseDoubleSafe(fields[columnIndex.get("avg_longitude")]);
                int daysOld = parseIntSafe(fields[columnIndex.get("days_old")]);
                int conjunctions = parseIntSafe(fields[columnIndex.get("conjunction_count")]);

                SpaceObject obj = SpaceObjectFactory.create(
                    objType,
                    id,
                    name,
                    country,
                    orbit,
                    launchYear,
                    launchSite,
                    longitude,
                    avgLongitude,
                    "", "", false, false, false,
                    daysOld, conjunctions
                );

                objectMap.put(id, obj);

            } catch (Exception e) {
                System.out.println("[ERROR] Failed to parse row: " + Arrays.toString(fields));
                e.printStackTrace();
            }
        }

        return objectMap;
    }

    /** Parses an integer safely with fallback to 0 */
    private static int parseIntSafe(String val){
        try {
            return Integer.parseInt(val.trim());
        } catch (Exception e) {
            return 0;
        }
    }

    /** Parses a double safely with fallback to 0.0 */
    private static double parseDoubleSafe(String val){
        try {
            return Double.parseDouble(val.trim());
        } catch (Exception e) {
            return 0.0;
        }
    }

    /** Splits a CSV line respecting quoted commas */
    public static String[] smartSplit(String line){
        List<String> tokens = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder sb = new StringBuilder();

        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                tokens.add(sb.toString().trim());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        tokens.add(sb.toString().trim());
        return tokens.toArray(new String[0]);
    }
}
