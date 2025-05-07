/**
 * Factory class for creating specific types of SpaceObject instances
 * based on the object type string from the dataset.
 * 
 * @author Caitlin Gregory
 * @author Daniela Gutierrez
 */
public class SpaceObjectFactory {

    /**Default constructor */
    SpaceObjectFactory(){}

    /**
     * Creates an appropriate subclass of SpaceObject based on the type string.
     * 
     * @param type          the type of object (e.g., "DEBRIS", "ROCKET BODY", etc.)
     * @param daysOld       how many days old the object is
     * @param recordId recordId
     * @param geohash the geohash
     * @param isNominated boolean
     * @param isUnknownObject boolean
     * @param conjunctionCount int number of conjunctions recorded
     * @param hrrCategory String
     * @param avgLongitude double with object's average longitude
     * @param longitude double with object's longitude
     * @param launchSite place object was launched
     * @param launchYear year it was launched
     * @param satelliteName name of the satellite
     * @param country country that launched it
     * @param orbitType the orbit category (LEO, MEO, GEO, etc.)
     * @param hasDossier boolean
     * @return the corresponding SpaceObject subclass
     */
    public static SpaceObject create(
        String type,
        String recordId, String satelliteName, String country, String orbitType,
        int launchYear, String launchSite, double longitude, double avgLongitude,
        String geohash, String hrrCategory, boolean isNominated,
        boolean hasDossier, boolean isUnknownObject, int daysOld, int conjunctionCount
    ) {
        switch (type == null ? "" : type.trim().toUpperCase()) {
            case "DEBRIS":
                return new Debris(recordId, satelliteName, country, orbitType,
                                launchYear, launchSite, longitude, avgLongitude,
                                geohash, hrrCategory, isNominated, hasDossier,
                                isUnknownObject, daysOld, conjunctionCount);
            case "ROCKET BODY":
                return new RocketBody(recordId, satelliteName, country, orbitType,
                                    launchYear, launchSite, longitude, avgLongitude,
                                    geohash, hrrCategory, isNominated, hasDossier,
                                    isUnknownObject, daysOld, conjunctionCount);
            case "PAYLOAD":
                return new Payload(recordId, satelliteName, country, orbitType,
                                launchYear, launchSite, longitude, avgLongitude,
                                geohash, hrrCategory, isNominated, hasDossier,
                                isUnknownObject, daysOld, conjunctionCount);
            default:
                return new UnknownObject(recordId, satelliteName, country, orbitType,
                                        launchYear, launchSite, longitude, avgLongitude,
                                        geohash, hrrCategory, isNominated, hasDossier,
                                        isUnknownObject, daysOld, conjunctionCount);
        }
    }
}
