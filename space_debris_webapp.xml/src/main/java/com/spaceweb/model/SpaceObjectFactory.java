/**
 * Factory class for creating specific types of SpaceObject instances
 * based on the object type string from the dataset.
 * 
 * @author Caitlin Gregory
 * @author Daniela Gutierrez
 */
package com.spaceweb.model;

public class SpaceObjectFactory {

    /**
     * Creates an appropriate subclass of SpaceObject based on the type string.
     * 
     * @param type          the type of object (e.g., "DEBRIS", "ROCKET BODY", etc.)
     * @param id            the record ID
     * @param orbit         the orbit category (LEO, MEO, GEO, etc.)
     * @param daysOld       how many days old the object is
     * @param conjunctions  number of conjunctions recorded
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
