/**
 * Class that performs all tracking, searching, and assessing functionality
 * @author Caitlin Gregory
 * @author Daniela Gutierrez
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MissionControl {
    /** Tracking System variable*/
    protected TrackingSystem trackingSystem; 
    /**Curent user variable */
    private User currentUser; //unused for now   

    /**
     * Mission control constructor that initializes a Tracking System object to read the csv file
     */
    public MissionControl(TrackingSystem trackingSystem) {
        this.trackingSystem = trackingSystem;
    }

    /**
     * Search a space object based on its type
     * @param choice type of object that the user wants to query
     */
    public void trackObjectsInSpace(int choice) {
        Logger logger = Logger.getInstance(); // Log start of query

        // Log query type
        switch (choice) {
            case 1:
                Logger.getInstance().log( " Scientist queried Rocket Body objects.");
                break;
            case 2:
                Logger.getInstance().log( " Scientist queried Debris objects.");
                break;
            case 3:
                Logger.getInstance().log( " Scientist queried Payload Body objects.");
                break;
            case 4:
                Logger.getInstance().log( " Scientist queried Unknown objects.");
                break;
            default:
                System.out.println("Invalid input");
                return;
        }

        // Print matching space objects
        for (SpaceObject obj : trackingSystem.getAllObjects().values()) {
            boolean match = false;

            switch (choice) {
                case 1:
                    if (obj instanceof RocketBody) match = true;
                    break;
                case 2:
                    if (obj instanceof Debris) match = true;
                    break;
                case 3:
                    if (obj instanceof Payload) match = true;
                    break;
                case 4:
                    if (obj instanceof UnknownObject) match = true;
                    break;
            }

            if (match) {
                obj.displayInfo();
                System.out.println("--------------------------------------------------");
            }
        }
    }

    /**
     * Assess if a space object of debris type is still in orbit by taking into consideration
     * its orbit type, longitude, age and conjunction count
     */
    public void assessDebrisStillInOrbit() {

        for (SpaceObject obj : trackingSystem.getAllObjects().values()) {
            if (obj instanceof Debris) {
                Debris debris = (Debris) obj;
                boolean stillInOrbit;
                double orbitalDrift = Math.abs((debris.longitude)-(debris.avgLongitude)); //calculate orbital drift
                String riskLevel;
                //check if still in orbit
                if ((!(debris.orbitType.equals("Unknown Orbit Category"))) && (debris.longitude != 0) && (debris.daysOld >= 0 && debris.daysOld < 15000) && debris.conjunctionCount >= 1) {
                    stillInOrbit = true;
                }else{
                    stillInOrbit = false;
                }

                //assess risk level
                if (orbitalDrift > 50) {
                    riskLevel = "High Risk";
                }else if (orbitalDrift > 10) {
                    riskLevel = "Moderate Risk";
                }else{
                    riskLevel = "Low Risk";
                }

                obj.displayInfo();  
                //display new info
                System.out.println("Still In Orbit: " + stillInOrbit);
                System.out.println("Conjunction Count: " + debris.conjunctionCount);
                System.out.println("Orbital Drift: " + orbitalDrift);
                System.out.println("Risk Level: " + riskLevel);
                System.out.println("-------------------------");

                //set variables in Debris object
                debris.setStillInOrbit(stillInOrbit);
                debris.setRiskLevel(riskLevel);
            }
        }
    }

    /**
     * Create a new CSV with the updated values of the Debris' orbit
     * @param outputPath file to write on
     */
    public void exportToUpdatedCSV(String outputPath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath))) {
            // Write header
            bw.write("record_id,norad_cat_id,satellite_name,country,approximate_orbit_type,object_type,launch_year,launch_site,longitude,avg_longitude,geohash,HRR_Category,is_nominated,nominated_at,has_dossier,last_updated_at,justification,focused_analysis,days_old,conjunction_count,is_unk_object,all_maneuvers,days_since_ob,recent_maneuvers,deltaV_90day,has_sister_debris,still_in_orbit,risk_level\n");

            for (SpaceObject obj : trackingSystem.getAllObjects().values()) {
                StringBuilder line = new StringBuilder();
                //Common fields
                line.append(obj.recordId).append(",");
                line.append(obj.recordId).append(","); //norad_cat_id, same as record_id
                line.append(obj.satelliteName).append(",");
                line.append(obj.country).append(",");
                line.append(obj.orbitType).append(",");
                line.append(obj.getClass().getSimpleName().toUpperCase()).append(",");
                line.append(obj.launchYear).append(",");
                line.append(obj.launchSite).append(",");
                line.append(obj.longitude).append(",");
                line.append(obj.avgLongitude).append(",");
                line.append(obj.geohash).append(","); 
                line.append(obj.hrrCategory).append(",");
                line.append(obj.isNominated).append(",,");
                line.append(obj.hasDossier).append(",,,,"); //fill missing fields
                line.append(obj.daysOld).append(",");
                line.append(obj.conjunctionCount).append(",");
                line.append("FALSE,,,,"); //rest of the columns
                //add new data if the object is Debris
                if (obj instanceof Debris) {
                    Debris d = (Debris) obj;
                    line.append(d.isStillInOrbit()).append(",");
                    line.append(d.getRiskLevel());
                } else {
                    line.append("N/A,N/A");
                }

                bw.write(line.toString());
                bw.newLine();
            }

        } catch (IOException e) { //catch error in csv writing
            System.out.println("Error writing updated CSV: " + e.getMessage());
        }
    }

    /** 
     * Populate a txt file with the count of in orbit and exited Debris objects and some information 
     * about them
     * @param txtPath file to write on
    */
    public void exportExitedDebrisReport(String txtPath){
        int inOrbitCount = 0;
        int exitedCount = 0;
        List<Debris> exitedDebris = new ArrayList<>();

        //count how many Debris objects are still in orbit and how many are not
        for (SpaceObject obj : trackingSystem.getAllObjects().values()) {
            if (obj instanceof Debris) {
                Debris d = (Debris) obj;
                if (d.isStillInOrbit()) {
                    inOrbitCount++;
                } else {
                    exitedCount++;
                    exitedDebris.add(d);
                }
            }
        }

        //print info about exited debris
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(txtPath))) {
            bw.write("Debris Orbit Assessment Report\n");
            bw.write("In-Orbit Count: " + inOrbitCount + "\n");
            bw.write("Exited Count: " + exitedCount + "\n\n");

            bw.write("Exited Debris List:\n");
            for (Debris d : exitedDebris) {
                bw.write(String.format("Record ID: %s | Name: %s | Country: %s | Orbit: %s | Year: %d | Site: %s | Long: %.2f | AvgLong: %.2f | Geohash: %s | Days Old: %d\n",
                    d.recordId, d.satelliteName, d.country, d.orbitType, d.launchYear,
                    d.launchSite, d.longitude, d.avgLongitude, d.geohash, d.daysOld));
            }
        } catch (IOException e) {
            System.out.println("Error writing debris report: " + e.getMessage());
        }
    }

    /**
     * Method that prints out all the objects with LEO orbit type
     */
    public void trackObjectsInLEO(){

        for (SpaceObject obj : trackingSystem.getAllObjects().values()) {
            if (obj.orbitType.equals("LEO")){
                obj.displayInfo();  //print info if object has LEO orbit type
                System.out.println("-------------------------");
            }
        }
    }
    /**
     * Space Agency Representative options:
     * Delegates the long-term impact analysis to the ImpactAnalysis class.
     * Filters LEO space objects that are older than 200 days and have at least 1 conjunction.
     */
    public void analyzeLongTermImpact(){
        List<SpaceObject> objectList = new ArrayList<>(trackingSystem.getAllObjects().values());
        ImpactAnalysis analysis = new ImpactAnalysis();
        analysis.analyzeLongTermImpact(objectList);
    }
    

    /**
     * Delegates the density report generation to DebrisDensityAnalysis.
     * Filters all space objects in the specified longitude range.
     * 
     * @param minLongitude Minimum longitude value
     * @param maxLongitude Maximum longitude value
     */
    public void generateDensityReport(double minLongitude, double maxLongitude) {
        List<SpaceObject> objectList = new ArrayList<>(trackingSystem.getAllObjects().values());
        DebrisDensityAnalysis analysis = new DebrisDensityAnalysis();
    
        try {
            analysis.generateDensityReport(objectList, minLongitude, maxLongitude);
        } catch (DensityReportException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }    
} 