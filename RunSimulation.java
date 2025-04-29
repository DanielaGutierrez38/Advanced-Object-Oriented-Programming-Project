//import java.util.Scanner;

/**
 * Class that contains the main method that runs the program 
 * @autor Caitlin Gregory
 * @autor Daniela Gutierrez
 */
class RunSimulation{

    /**Main method of the program 
     * @param args main method thing
     */
    public static void main(String[] args){

        TrackingSystem trackingSystem = new TrackingSystem("rso_metrics_columns_jumbled.csv");  // Load the file
        MissionControl missionControl = new MissionControl(trackingSystem);     // Create MissionControl with TrackingSystem
        DisplayMenu menu = new DisplayMenu(missionControl);                     // PASS MissionControl here
        menu.displayMainMenu();                                                 // Display the menu
    }
}
