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
    Logger newLogger = new Logger();

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
                        newLogger.logScientistIn();
                        displayScientistMenu();
                    break;

                    case 2:
                        newLogger.logSpaceAgentRepIn();
                        displaySpaceAgentRepMenu();
                    break;

                    case 3:
                        newLogger.logPolicymakerIn();
                        displayPolicymakerMenu();
                    break;

                    case 4:
                        newLogger.logAdministratorIn();
                        displayAdministratorMenu();
                    break;

                    case 5:
                        //input.close();
                        newLogger.updateCSVFile();
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
                                    newLogger.logQueryTrackLEOObjects();
                                    missionControl.trackObjectsInLEO();
                    
                                break;

                                case 2:
                                    //orbit assessment
                                    newLogger.logDebrisOrbitStatus();
                                    missionControl.assessDebrisStillInOrbit();
                                    newLogger.logCreateExitedDebrisTxtFile();
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
                        //Logger newLogger = new Logger();
                        newLogger.logScientistExit();
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
        int userSelectionSAR = inputSAR.nextInt();
        Logger spaceAgentLogger = new Logger();

        try{
            if (userSelectionSAR == 3) {
                //inputSAR.close();
                spaceAgentLogger.logSpaceAgentRepExit();
                displayMainMenu();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number between 1 and 3.");
            inputSAR.next();
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
        Logger policymakerLogger = new Logger();

        try{
            if (userSelectionPM == 3) {
                //inputPM.close();
                policymakerLogger.logPolicymakerExit();
                displayMainMenu();
            }
        } catch (InputMismatchException e) {
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
        Logger administratorLogger = new Logger();

        try{
            if (userSelectionAdmin == 4) {
                //inputAdmin.close();
                administratorLogger.logAdministratorExit();
                displayMainMenu();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number between 1 and 4.");
            inputAdmin.next();
        }

    }

}
