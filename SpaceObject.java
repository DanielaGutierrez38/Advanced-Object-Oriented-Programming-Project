/**
 * The SpaceObject class serves as an abstract base class for objects in space
 * such as Debris and Satellite.
 * 
 * It contains common properties and methods shared by all space objects.
 * This class is extended by Debris and Satellite.
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
    protected String hrrCategory;
    protected boolean isNominated;
    protected boolean hasDossier;
    protected boolean isUnknownObject;
    protected int daysOld;

    public SpaceObject(String recordId, String satelliteName, String country, String orbitType,
                       int launchYear, String launchSite, double longitude, double avgLongitude,
                       String geohash, String hrrCategory, boolean isNominated, boolean hasDossier,
                       boolean isUnknownObject, int daysOld) {
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
    }

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

    public String getHRRCategory() {
        return hrrCategory;
    }

    public void setHRRCategory(String hrrCategory) {
        this.hrrCategory = hrrCategory;
    }
}
