/**
 * The Debris class represents a space object classified as debris.
 * It extends SpaceObject, adds stillInOrbit and riskLevel after the orbit status is assessed by a Scientist user.
 * 
 * @author Caitlin Gregory
 * @author Daniela Gutierrez
 */
package com.spaceweb.model;

public class Debris extends SpaceObject {

    /** This variable is true if the Debris is still in orbit, false if its not */
    private boolean stillInOrbit;
    /** Debris risk Level can be high, moderate or low */
    private String riskLevel;

    /**
     *Default constructor for testing only what we need for assess debris still in orbit
     */
    public Debris(){}

    /**
     * Constructs a Satellite object with the provided attributes.
     *
     * @param recordId Unique record ID of the debris.
     * @param satelliteName Name of the debris.
     * @param country Country of origin.
     * @param orbitType Orbit type (LEO, GEO, etc.).
     * @param launchYear Year the debris was launched.
     * @param launchSite Code for the launch site.
     * @param longitude Current longitude of the debris.
     * @param avgLongitude Average longitude over its orbit.
     * @param geohash Geospatial hash representing approximate location.
     * @param hrrCategory Human Research Roadmap?
     * @param isNominated Nominated boolean
     * @param hasDossier Detailed records on the Debris
     * @param isUnknownObject Known status of the Debris
     * @param daysOld Number of days since launch.
     * @param conjunctionCount Recent interactions 
     */
    public Debris(String recordId, String satelliteName, String country, String orbitType,
                  int launchYear, String launchSite, double longitude, double avgLongitude,
                  String geohash, String hrrCategory, boolean isNominated,
                  boolean hasDossier, boolean isUnknownObject, int daysOld, int conjunctionCount) {
        super(recordId, satelliteName, country, orbitType, launchYear, launchSite,
              longitude, avgLongitude, geohash, hrrCategory, isNominated,
              hasDossier, isUnknownObject, daysOld, conjunctionCount);
    }

    /** Getter for stillInOrbit
     * @return stillInOrbit boolean
    */
    public boolean isStillInOrbit(){
        return stillInOrbit;
    }

    /**Setter for stillInOrbit 
     * @param stillInOrbit boolean
    */
    public void setStillInOrbit(boolean stillInOrbit){
        this.stillInOrbit = stillInOrbit;
    }

    /** Getter for riskLevel
     * @return String riskLevel
    */
    public String getRiskLevel(){
        return riskLevel;
    }

    /**Setter for riskLevel 
     * @param riskLevel String
    */
    public void setRiskLevel(String riskLevel){
        this.riskLevel = riskLevel;
    }

    /**Display Debris information */
    @Override  
    public void displayInfo(){
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
