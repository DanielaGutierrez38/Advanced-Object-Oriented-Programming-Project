/**
 * Class that represents a Rocket Body object
 * @author Caitlin Gregory
 * @author Daniela Gutierrez
 */
public class RocketBody extends SpaceObject {

    /**
     * Constructs a RocketBody object with the provided attributes.
     *
     * @param recordId Unique record ID of the rocket body.
     * @param satelliteName Name of the rocket body.
     * @param country Country of origin.
     * @param orbitType Orbit type (LEO, GEO, etc.).
     * @param launchYear Year the rocket body was launched.
     * @param launchSite Code for the launch site.
     * @param longitude Current longitude of the rocket body.
     * @param avgLongitude Average longitude over its orbit.
     * @param geohash Geospatial hash representing approximate location.
     * @param hrrCategory Human Research Roadmap?
     * @param isNominated Nominated boolean
     * @param hasDossier Detailed records on the RocketBody
     * @param isUnknownObject Known status of the rocket body
     * @param daysOld Number of days since launch.
     * @param conjunctionCount Recent interactions 
     */
    public RocketBody(String recordId, String satelliteName, String country, String orbitType,
                      int launchYear, String launchSite, double longitude, double avgLongitude,
                      String geohash, String hrrCategory, boolean isNominated,
                      boolean hasDossier, boolean isUnknownObject, int daysOld, int conjunctionCount) {
        super(recordId, satelliteName, country, orbitType, launchYear, launchSite,
              longitude, avgLongitude, geohash, hrrCategory, isNominated, hasDossier, isUnknownObject, daysOld, conjunctionCount);
    }

    /**Method to display information */
    @Override
    public void displayInfo() {
      System.out.println("Record ID: " + recordId);
      System.out.println("Satellite Name: " + satelliteName);
      System.out.println("Country: " + country);
      System.out.println("Orbit Type: " + orbitType);
      System.out.println("Launch Year: " + launchYear);
      System.out.println("Launch Site: " + launchSite);
      System.out.println("Longitude: " + longitude);
      System.out.println("Avg Longitude: " + avgLongitude);
      System.out.println("Geohash: " + geohash);
      System.out.println("Days Old: " + daysOld);
    }
}