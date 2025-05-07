/**
 * Class that represents a Payload object. Child class of SpaceObject
 * @author Caitlin Gregory
 * @author Daniela Gutierrez
 */
package com.spaceweb.model;

public class Payload extends SpaceObject {

    /**
     * Constructs a Payload object with the provided attributes.
     *
     * @param recordId Unique record ID of the payload.
     * @param satelliteName Name of the payload.
     * @param country Country of origin.
     * @param orbitType Orbit type (LEO, GEO, etc.).
     * @param launchYear Year the payload was launched.
     * @param launchSite Code for the launch site.
     * @param longitude Current longitude of the payload.
     * @param avgLongitude Average longitude over its orbit.
     * @param geohash Geospatial hash representing approximate location.
     * @param hrrCategory Human Research Roadmap?
     * @param isNominated Nominated boolean
     * @param hasDossier Detailed records on the Payload
     * @param isUnknownObject Known status of the Debris
     * @param daysOld Number of days since launch.
     * @param conjunctionCount Recent interactions 
     */
    public Payload(String recordId, String satelliteName, String country, String orbitType,
                   int launchYear, String launchSite, double longitude, double avgLongitude,
                   String geohash, String hrrCategory, boolean isNominated,
                   boolean hasDossier, boolean isUnknownObject, int daysOld, int conjunctionCount) {
        super(recordId, satelliteName, country, orbitType, launchYear, launchSite,
              longitude, avgLongitude, geohash, hrrCategory, isNominated, hasDossier, isUnknownObject, daysOld, conjunctionCount);
    }

    /**Print Payload's info */
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