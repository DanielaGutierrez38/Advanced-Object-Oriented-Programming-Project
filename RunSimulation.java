/**
 * Class that contains the main method that runs the program 
 * @autor Caitlin Gregory
 * @autor Daniela Gutierrez
 */
class RunSimulation{ 

    /**Menu interface is a parameter*/
    private final Menu menu;

    /**Constructor that takes in the Menu interface */
    public RunSimulation(Menu menu) {
        this.menu = menu;
    }

    /**Main method of the program 
     * @param args main method thing
     */
    public static void main(String[] args){

        TrackingSystem trackingSystem = new TrackingSystem("rso_metrics_columns_jumbled.csv");  // Load the file
        MissionControl missionControl = new MissionControl(trackingSystem);     // Create MissionControl with TrackingSystem
        UserManager userManager = new UserManager("user_info.csv"); //create user manager 
        Menu menu = new DisplayMenu(missionControl, userManager);                     // PASS MissionControl here
        menu.displayMainMenu();                                                 // Display the menu
    }
}
