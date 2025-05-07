/**
 * The SpaceObject class serves as an abstract base class for objects in space
 * such as Debris and Satellite.
 * 
 * It contains common properties and methods shared by all space objects.
 * This class is extended by Debris and Satellite.
 * @author Caitlin Gregory
 * @author Daniela Gutierrez
 */
package com.spaceweb.model;

public abstract class SpaceObject {

    /**record id */
    protected String recordId;
    /**name of the satellite */
    protected String satelliteName;
    /**country of origin */
    protected String country;
    /**type of orbit */
    protected String orbitType;
    /**year it was launched */
    protected int launchYear;
    /**place it was launched */
    protected String launchSite;
    /**longitude of the object */
    protected double longitude;
    /**average longitude of the object */
    protected double avgLongitude;
    /**geohash of the object */
    protected String geohash;
    /**hrr category */
    protected String hrrCategory;
    /**is nominated boolean */
    protected boolean isNominated;
    /**dossier */
    protected boolean hasDossier;
    /**unknown object boolean */
    protected boolean isUnknownObject;
    /**number of days since launch */
    protected int daysOld;
    /**recent interactions */
    protected int conjunctionCount;

    /**
     * Default constructor for testing debris
     */
    public SpaceObject(){}

    /**
     * Constructs a Space object with the provided attributes.
     *
     * @param recordId Unique record ID of the Space Object.
     * @param satelliteName Name of the Space Object.
     * @param country Country of origin.
     * @param orbitType Orbit type (LEO, GEO, etc.).
     * @param launchYear Year the Space Object was launched.
     * @param launchSite Code for the launch site.
     * @param longitude Current longitude of the Space Object.
     * @param avgLongitude Average longitude over its orbit.
     * @param geohash Geospatial hash representing approximate location.
     * @param hrrCategory Human Research Roadmap?
     * @param isNominated Nominated boolean
     * @param hasDossier Detailed records on the space object
     * @param isUnknownObject Known status of the space object
     * @param daysOld Number of days since launch.
     * @param conjunctionCount Recent interactions
     */
    public SpaceObject(String recordId, String satelliteName, String country, String orbitType,
                       int launchYear, String launchSite, double longitude, double avgLongitude,
                       String geohash, String hrrCategory, boolean isNominated, boolean hasDossier,
                       boolean isUnknownObject, int daysOld, int conjunctionCount) {
        this.recordId = recordId;
        this.satelliteName = satelliteName;
        this.country = country;
        this.orbitType = orbitType;
        this.launchYear = launchYear;
        this.launchSite = launchSite;
        this.longitude = longitude;
        this.avgLongitude = avgLongitude;
        this.geohash = geohash;
        this.hrrCategory = hrrCategory;
        this.isNominated = isNominated;
        this.hasDossier = hasDossier;
        this.isUnknownObject = isUnknownObject;
        this.daysOld = daysOld;
        this.conjunctionCount = conjunctionCount;
    }

    /**Method to display space object info */
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
        System.out.println("HRR Category: " + hrrCategory);
        System.out.println("Is Nominated: " + isNominated);
        System.out.println("Has Dossier: " + hasDossier);
        System.out.println("Is Unknown Object: " + isUnknownObject);
        System.out.println("Days Old: " + daysOld);
    }

    /**HRR Category getter
     * @return hrrCategory
     */
    public String getHRRCategory() {
        return hrrCategory;
    }

    /**HRR Category Setter
     * @param hrrCategory hrr category
     */
    public void setHRRCategory(String hrrCategory) {
        this.hrrCategory = hrrCategory;
    }
}
