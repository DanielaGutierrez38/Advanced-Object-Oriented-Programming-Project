/**
 * The abstract SpaceObject class represents a general object in space, such as a debris or a satellite.
 * It holds shared attributes like record ID, satellite name, country of origin, orbit type, and more.
 * <p>
 * Debris and Satellite classes extend this abstract base class.
 * </p>
 * 
 * @author Caitlin Gregory
 * @author Daniela Gutierrez
 */

public abstract class SpaceObject {
    protected String recordId;
    protected String satelliteName;
    protected String country;
    protected String orbitType;
    protected int launchYear;
    protected String launchSite;
    protected double longitude;
    protected double avgLongitude;
    protected String geohash;
    protected int daysOld;

    /**
     * Constructs a new SpaceObject with the given parameters.
     * 
     * @param recordId The unique identifier for the object.
     * @param satelliteName The name of the satellite or debris.
     * @param country The country of origin.
     * @param orbitType The orbit type (LEO, GEO, etc.).
     * @param launchYear The year the object was launched.
     * @param launchSite The site from which the object was launched.
     * @param longitude The current longitude of the object.
     * @param avgLongitude The average longitude.
     * @param geohash The geospatial hash.
     * @param daysOld Number of days since launch.
     */
    public SpaceObject(String recordId, String satelliteName, String country, String orbitType,
                       int launchYear, String launchSite, double longitude, double avgLongitude,
                       String geohash, int daysOld) {
        this.recordId = recordId;
        this.satelliteName = satelliteName;
        this.country = country;
        this.orbitType = orbitType;
        this.launchYear = launchYear;
        this.launchSite = launchSite;
        this.longitude = longitude;
        this.avgLongitude = avgLongitude;
        this.geohash = geohash;
        this.daysOld = daysOld;
    }

    /**
     * Displays the full information of the SpaceObject in a readable format.
     */
    public void displayInfo() {
        System.out.println("Record ID: " + recordId);
        System.out.println("Satellite Name: " + satelliteName);
        System.out.println("Country: " + country);
        System.out.println("Orbit Type: " + orbitType);
        System.out.println("Launch Year: " + launchYear);
        System.out.println("Launch Site: " + launchSite);
        System.out.println("Longitude: " + longitude);
        System.out.println("Average Longitude: " + avgLongitude);
        System.out.println("Geohash: " + geohash);
        System.out.println("Days Old: " + daysOld);
    }

    /**
     * Calculates the orbital drift (difference between current and average longitude).
     * 
     * @return The absolute value of the difference between longitude and avgLongitude.
     */
    public double calculateDrift() {
        return Math.abs(longitude - avgLongitude);
    }
    /**
     * Returns the number of days the object has been in orbit.
     * 
     * @return Days old (days since launch).
     */
    public int getAge() {
        return daysOld;
    }
    
    /**
     * Prepares a single line of CSV-formatted data representing this object.
     * 
     * @return A string with comma-separated values for saving to a CSV file.
     */
    public String toCSVRow() {
        return String.join(",",
            recordId,
            satelliteName,
            country,
            orbitType,
            String.valueOf(launchYear),
            launchSite,
            String.valueOf(longitude),
            String.valueOf(avgLongitude),
            geohash,
            String.valueOf(daysOld)
        );
    }
}
