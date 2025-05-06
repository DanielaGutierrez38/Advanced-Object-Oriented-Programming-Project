import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.*;

/**
 * This class handles all menu interactions for all users
 * @author Caitlin Gregory
 * @author Daniela Gutierrez
 */
public class DisplayMenu {   
// Interface 
    /**Default constructor */
    private MissionControl missionControl;

    /** Create a new Logger variable to log user interactions */
    Logger logger = Logger.getInstance();

    /** Create a new MissionControl variable to call its methods to track, search and assess */
    public DisplayMenu(MissionControl missionControl) {
        this.missionControl = missionControl;
    }

    /**
     * Method that displays the main menu
     */
    public void displayMainMenu(){
        int userSelection = 1;

        do{

            System.out.println("------------------Space Debris in LEO Tracker------------------");
            System.out.println("Welcome! Please select the number corresponding to your user type");
            System.out.println("1-. Scientist");
            System.out.println("2-. Space Agency Representative");
            System.out.println("3-. Policymaker");
            System.out.println("4-. Administrator");
            System.out.println("5-. Exit");

            Scanner input = new Scanner(System.in);

            try{
                userSelection = input.nextInt();

                switch(userSelection){

                    case 1:
                        Logger.getInstance().log(" Scientist User logged in");
                        displayScientistMenu();
                    break;

                    case 2:
                        Logger.getInstance().log(" Space Agent Representative User logged in");
                        displaySpaceAgentRepMenu();
                    break;

                    case 3:
                        Logger.getInstance().log(" Policymaker User logged in");
                        displayPolicymakerMenu();
                    break;

                    case 4:
                        Logger.getInstance().log(" Administrator User logged in");
                        displayAdministratorMenu();
                    break;

                    case 5:
                        //input.close();
                        Logger.getInstance().log(" CSV File was updated with debris orbit status");
                        missionControl.exportToUpdatedCSV("updated_rso_metrics.csv");
                        System.out.println("Thank you for using the system! See you next time :)");
                        System.exit(0);

                    break;

                    default:
                        System.out.println("Invalid input. Please select a number between 1 and 4");
                    break;

                }//end switch

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                input.next();
            }

        }while (userSelection != 5);//end while

    }//end displayMainMenu

    /**
    * Method that displays the Scientist menu once the user has logged in as this user
    */
    public void displayScientistMenu() {
        Scanner inputScientist = new Scanner(System.in);
        int userSelectionScientist = 1;

        do {
            System.out.println("........................User: Scientist........................");
            System.out.println("Please select the number for the action that you want to perform");
            System.out.println("1-. Track Objects in Space");
            System.out.println("2-. Assess Orbit Status");
            System.out.println("3-. Back");

            try{
                userSelectionScientist = inputScientist.nextInt();

                switch (userSelectionScientist) {
                    case 1:
                    
                        System.out.println("Please select the type of object that you want to track: ");
                        System.out.println("1-. Rocket Body");
                        System.out.println("2-. Debris");
                        System.out.println("3-. Payload");
                        System.out.println("4-. Unknown");

                        try{
                            int objectTrack = inputScientist.nextInt();

                            if (objectTrack >= 1 && objectTrack <= 4) {
                                missionControl.trackObjectsInSpace(objectTrack); // passes choice
                            } else {
                                System.out.println("Invalid input. Please enter a number between 1 and 4");
                            }

                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a number between 1 and 4.");
                            inputScientist.next();
                        }

                    break;

                    case 2:
                        System.out.println("Please select the action that you want to perform");
                        System.out.println("1-. Track Objects in LEO");
                        System.out.println("2-. Assess if debris is still in orbit");
                        //Scientist newScientist = new Scientist();

                        try{
                            int orbitStatus = inputScientist.nextInt();

                            switch (orbitStatus) {
                                case 1:

                                    //display list of all objects in the LEO and their info
                                    Logger.getInstance().log(" Scientist queried LEO Objects");
                                    missionControl.trackObjectsInLEO();
                    
                                break;

                                case 2:
                                    //orbit assessment
                                    Logger.getInstance().log(" Scientist assessed orbit status of Debris");
                                    missionControl.assessDebrisStillInOrbit();
                                    Logger.getInstance().log(" TXT file was created with inorbit and exited debris count");
                                    missionControl.exportExitedDebrisReport("inorbit_exited_debris_report.txt");
                                break;

                                default:
                                    System.out.println("Invalid input. Please select a number between 1 and 2");
                                break;
                            }

                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a number between 1 and 2.");
                            inputScientist.next();
                        }

                    break;

                    case 3:
                        Logger.getInstance().log(" Scientist User logged out");
                        displayMainMenu(); // Go back to main menu
                        break;

                    default:
                        System.out.println("Invalid input. Please enter a number between 1 and 3");
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                inputScientist.next();
            }

        } while (userSelectionScientist != 3);
    }

    /**
     * Method that displays the Space Agent Representative menu once the user has logged in as this user
     */
    public void displaySpaceAgentRepMenu(){

        System.out.println("...............User: Space Agency Representative...............");
        System.out.println("Please select the number for the action that you want to perform");
        System.out.println("1-. Analyze long-term Impact");
        System.out.println("2-. Generate Density Reports");
        System.out.println("3-. Back");

        Scanner inputSAR = new Scanner(System.in);
        int userSelectionSAR = -1;

        try {
            userSelectionSAR = inputSAR.nextInt();  // Get input

            switch (userSelectionSAR){
                case 1:
                    // Call the method to analyze long-term impact
                    Logger.getInstance().log(" Space Agent Rep selected Analyze Long-Term Impact");
                    missionControl.analyzeLongTermImpact();
                    break;

                case 2:
                    // Prompt and call density report
                    Logger.getInstance().log("Space Agent Rep selected Generate Density Report");

                    while (true) {
                        try {
                            System.out.print("Enter minimum longitude: ");
                            double minLong = inputSAR.nextDouble();

                            System.out.print("Enter maximum longitude: ");
                            double maxLong = inputSAR.nextDouble();

                            missionControl.generateDensityReport(minLong, maxLong);
                            break; // exit loop after successful input and report generation

                        } catch (InputMismatchException e) {
                            System.out.println("[ERROR] Invalid input. Please enter numeric values for longitude.");
                            inputSAR.nextLine(); // clear invalid input from scanner

                        } catch (Exception e) {
                            System.out.println("[ERROR] An unexpected error occurred: " + e.getMessage());
                            break; // break to avoid infinite loop
                        }
                    }
                case 3:
                    Logger.getInstance().log(" Space Agent Representative User logged out");
                    displayMainMenu();  // Return to main menu
                    break;

                default:
                    System.out.println("Invalid input. Please enter a number between 1 and 3.");
            }

        } catch (InputMismatchException e){
            System.out.println("Invalid input. Please enter a valid number.");
            inputSAR.next();  // Clear bad input
        }

        // Re-display the SAR menu until they choose to go back
        if (userSelectionSAR != 3){
            displaySpaceAgentRepMenu();
        }
    }


    /**
    * Method that displays the Policymaker menu once the user has logged in as this user
    */
    public void displayPolicymakerMenu(){

        System.out.println(".......................User: Policymaker.......................");
        System.out.println("Please select the number for the action that you want to perform");
        System.out.println("1-. Review Reports on Debris Impact");
        System.out.println("2-. Assess Risk Levels for Future Space Missions");
        System.out.println("3-. Back");

        Scanner inputPM = new Scanner(System.in);
        int userSelectionPM = inputPM.nextInt();

        try{
            if (userSelectionPM == 3){
                Logger.getInstance().log(" Policymaker User logged out");
                displayMainMenu();
            }
        } catch (InputMismatchException e){
            System.out.println("Invalid input. Please enter a number between 1 and 3.");
            inputPM.next();
        }

    }

    /**
    * Method that displays the Administrator menu once the user has logged in as this user
    */
    public void displayAdministratorMenu(){

        System.out.println("......................User: Administrator......................");
        System.out.println("Please select the number for the action that you want to perform");
        System.out.println("1-. Create User");
        System.out.println("2-. Manage User");
        System.out.println("3-. Delete User"); 
        System.out.println("4-. Back");

        Scanner inputAdmin = new Scanner(System.in);
        int userSelectionAdmin = inputAdmin.nextInt();

        try{
            if (userSelectionAdmin == 4){
                Logger.getInstance().log( " Administrator User logged out");
                displayMainMenu();
            }
        } catch (InputMismatchException e){
            System.out.println("Invalid input. Please enter a number between 1 and 4.");
            inputAdmin.next();
        }

    }

}
