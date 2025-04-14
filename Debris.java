/**
 * The Debris class represents a space object classified as debris.
 * <p>
 * It extends the SpaceObject class and adds specific attributes such as conjunction count,
 * orbital status, and risk level.
 * </p>
 * 
 * @author Caitlin Gregory
 * @author Daniela Gutierrez
 */

public class Debris extends SpaceObject {
    private long conjunctionCount;   // Number of times this debris was involved in conjunctions (potential collisions)
    private boolean stillInOrbit;     // Whether the debris is still orbiting
    private String riskLevel;         // Risk level (High, Moderate, Low)

    /**
     * Constructs a Debris object with all necessary attributes.
     * Initializes the debris as still in orbit and with an unknown risk level.
     *
     * @param recordId Unique record ID.
     * @param satelliteName Name of the debris object.
     * @param country Country of origin.
     * @param orbitType Orbit type (e.g., LEO, GEO).
     * @param launchYear Year of launch.
     * @param launchSite Launch site code.
     * @param longitude Current longitude.
     * @param avgLongitude Average longitude.
     * @param geohash Geospatial hash location.
     * @param daysOld Number of days since launch.
     * @param conjunctionCount Number of conjunction events involving this debris.
     */
    public Debris(String recordId, String satelliteName, String country, String orbitType,
            int launchYear, String launchSite, double longitude, double avgLongitude,
            String geohash, int daysOld, long conjunctionCount) {
        super(recordId, satelliteName, country, orbitType, launchYear, launchSite, longitude, avgLongitude, geohash, daysOld);
        this.conjunctionCount = conjunctionCount;
        this.stillInOrbit = true;    // Default
        this.riskLevel = "Unknown";  // Default
    }

    /**
     * Displays full information of the debris object, including conjunction count and risk details.
     */
    @Override
    public void displayInfo() {
        super.displayInfo(); // Call SpaceObject's displayInfo first
        System.out.println("Conjunction Count: " + conjunctionCount);
        System.out.println("Still In Orbit: " + stillInOrbit);
        System.out.println("Risk Level: " + riskLevel);
    }

    /**
     * Returns whether the debris is still in orbit.
     *
     * @return True if still orbiting, false if not.
     */
    public boolean isStillInOrbit() {
        return stillInOrbit;
    }

    /**
     * Sets whether the debris is still in orbit.
     *
     * @param stillInOrbit Boolean indicating orbital status.
     */
    public void setStillInOrbit(boolean stillInOrbit) {
        this.stillInOrbit = stillInOrbit;
    }

    /**
     * Returns the risk level of the debris.
     *
     * @return Risk level as a String ("High", "Moderate", "Low").
     */
    public String getRiskLevel() {
        return riskLevel;
    }

    /**
     * Sets the risk level of the debris.
     *
     * @param riskLevel The risk level to assign ("High", "Moderate", "Low").
     */
    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    /**
     * Returns the conjunction count for this debris object.
     *
     * @return The number of conjunction events.
     */
    public long getConjunctionCount() {
        return conjunctionCount;
    }
}
