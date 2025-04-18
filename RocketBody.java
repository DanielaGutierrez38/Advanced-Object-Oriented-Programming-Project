public class RocketBody extends SpaceObject {
    public RocketBody(String recordId, String satelliteName, String country, String orbitType,
                      int launchYear, String launchSite, double longitude, double avgLongitude,
                      String geohash, String hrrCategory, boolean isNominated,
                      boolean hasDossier, boolean isUnknownObject, int daysOld) {
        super(recordId, satelliteName, country, orbitType, launchYear, launchSite,
              longitude, avgLongitude, geohash, hrrCategory, isNominated, hasDossier, isUnknownObject, daysOld);
    }
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