import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.Console;
import java.lang.*;

/**
 * This class handles all menu interactions for all users
 * @author Caitlin Gregory
 * @author Daniela Gutierrez
 */
public class DisplayMenu implements Menu{   
// Interface that calls methods (separate class)
//call interface in run simulation
//pattern: singleton/factory (include in class diagram)
//singleton for logger
    /**Default constructor */
    private MissionControl missionControl;
    private final UserManager userManager;

    /** Create a new Logger variable to log user interactions */
    Logger newLogger = new Logger();

    /** Create a new MissionControl variable to call its methods to track, search and assess */
    public DisplayMenu(MissionControl missionControl, UserManager userManager) {
        this.missionControl = missionControl;
        this.userManager = userManager;
    }

    /**
     * Method that displays the main menu
     */
    @Override
    public void displayMainMenu() {
    
        int userSelection = 1;
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("------------------Space Debris in LEO Tracker------------------");
            System.out.println("Welcome! Please select the number corresponding to your user type");
            System.out.println("1-. Scientist");
            System.out.println("2-. Space Agency Representative");
            System.out.println("3-. Administrator");
            System.out.println("4-. Exit");

            try {
                userSelection = input.nextInt();
                input.nextLine();

                if (userSelection >= 1 && userSelection <= 3) {
                    System.out.println("1-. Log In");
                    System.out.println("2-. Create New User");
                    int authChoice = input.nextInt();
                    input.nextLine();

                    System.out.print("Enter username: ");
                    String username = input.nextLine();

                    Console console = System.console();
                    String password;
                    if (console != null) {
                        char[] passwordChars = console.readPassword("Enter password: ");
                        password = new String(passwordChars);
                    } else {
                        System.out.print("Enter password (not hidden): ");
                        password = input.nextLine();
                    }

                    String userType = switch (userSelection) {
                        case 1 -> "Scientist";
                        case 2 -> "SpaceAgentRep";
                        case 3 -> "Administrator";
                        default -> "";
                    };

                    if (authChoice == 1) {
                        boolean success = userManager.authenticateUser(username, password, userType);
                        if (success) {
                            newLogger.log(" " + userType + " user '" + username + "' logged in");
                            switch (userType) {
                                case "Scientist" -> displayScientistMenu();
                                case "SpaceAgentRep" -> displaySpaceAgentRepMenu();
                                case "Administrator" -> displayAdministratorMenu();
                            }
                        } else {
                            System.out.println("Login failed. Please check your credentials.");
                        }
                    } else if (authChoice == 2) {
                        if (userManager.getUser(username) != null) {
                            System.out.println("User already exists. Please choose a different username.");
                        } else {
                            Administrator tempAdmin = new Administrator("temp", "", "", userManager);
                            tempAdmin.createUser(username, password, userType);
                        }
                    } else {
                        System.out.println("Invalid choice.");
                    }

                } else if (userSelection == 4) {
                    newLogger.log(" CSV File was updated with debris orbit status");
                    missionControl.exportToUpdatedCSV("updated_rso_metrics.csv");
                    System.out.println("Thank you for using the system! See you next time :)");
                    System.exit(0);
                } else {
                    System.out.println("Invalid input. Please select a number between 1 and 4.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                input.nextLine();
            }

        } while (userSelection != 5);
    }

    /**
    * Method that displays the Scientist menu once the user has logged in as this user
    */
    @Override
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
                                    newLogger.log(" Scientist queried LEO Objects");
                                    missionControl.trackObjectsInLEO();
                    
                                break;

                                case 2:
                                    //orbit assessment
                                    newLogger.log(" Scientist assessed orbit status of Debris");
                                    missionControl.assessDebrisStillInOrbit();
                                    newLogger.log(" TXT file was created with inorbit and exited debris count");
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
                        newLogger.log(" Scientist User logged out");
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
    @Override
    public void displaySpaceAgentRepMenu(){

        Scanner inputSAR = new Scanner(System.in);
        int userSelectionSAR = 1;

        do {
            System.out.println("...............User: Space Agency Representative...............");
            System.out.println("Please select the number for the action that you want to perform");
            System.out.println("1-. Analyze long-term Impact");
            System.out.println("2-. Generate Density Reports");
            System.out.println("3-. Back");

            try{

                userSelectionSAR = inputSAR.nextInt();

                switch(userSelectionSAR){

                    case 1:
                        //analyze long term impact 
                    break;

                    case 2:
                        //generate density reports
                    break;
                
                    case 3:
                        //inputSAR.close();
                        newLogger.log(" Space Agent Representative User logged out");
                        displayMainMenu();
                    break;

                    default:
                        System.out.println("Invalid input. Please enter a number between 1 and 3");
                    break;
                
                } //end of switch

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                inputSAR.next();
            }

        }while(userSelectionSAR != 3);

    }

    /**
    * Method that displays the Administrator menu once the user has logged in as this user
    */
    @Override
    public void displayAdministratorMenu() {
        Scanner inputAdmin = new Scanner(System.in);
        int userSelectionAdmin = 1;
    
        //get the logged-in Administrator user to access methods
        Administrator admin = null;
        for (User u : userManager.getAllUsers()) {
            if (u instanceof Administrator a) {
                admin = a;
                break;
            }
        }
        if (admin == null) {
            System.out.println("Error: No Administrator instance available.");
            return;
        }
    
        do {
            System.out.println("......................User: Administrator......................");
            System.out.println("Please select the number for the action that you want to perform");
            System.out.println("1-. Create User");
            System.out.println("2-. Manage User");
            System.out.println("3-. Delete User");
            System.out.println("4-. Back");
    
            try {
                userSelectionAdmin = inputAdmin.nextInt();
                inputAdmin.nextLine(); 
    
                switch (userSelectionAdmin) {

                    case 1: //create a new user
                        System.out.print("Enter new username: ");
                        String newUsername = inputAdmin.nextLine();
                        System.out.print("Enter password: ");
                        String newPassword = inputAdmin.nextLine();
                        System.out.print("Enter user type (Scientist / SpaceAgentRep / Administrator): ");
                        String newType = inputAdmin.nextLine();
                        admin.createUser(newUsername, newPassword, newType);
                        break;
    
                    case 2: //manage (update) an existing user's username/password
                        System.out.print("Enter existing username: ");
                        String oldUsername = inputAdmin.nextLine();
    
                        System.out.println("What would you like to update?");
                        System.out.println("1-. Username only");
                        System.out.println("2-. Password only");
                        System.out.println("3-. Both username and password");
                        int updateChoice = inputAdmin.nextInt();
                        inputAdmin.nextLine();
    
                        String updatedUsername = oldUsername;
                        String updatedPassword = "[KEEP_OLD_PASSWORD]";
    
                        if (updateChoice == 1 || updateChoice == 3) {
                            System.out.print("Enter new username: ");
                            updatedUsername = inputAdmin.nextLine();
                        }
    
                        if (updateChoice == 2 || updateChoice == 3) {
                            System.out.print("Enter new password: ");
                            updatedPassword = inputAdmin.nextLine();
                        }
    
                        admin.updateUserFlexible(oldUsername, updatedUsername, updatedPassword);
                        break;
    
                    case 3: //delete a user
                        System.out.print("Enter username to delete: ");
                        String deleteUsername = inputAdmin.nextLine();
                        admin.deleteUser(deleteUsername);
                        break;
    
                    case 4: //go back
                        newLogger.log(" Administrator User logged out");
                        return;
    
                    default:
                        System.out.println("Invalid input. Please enter a number between 1 and 4.");
                        break;
                }
    
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                inputAdmin.next();
            }
    
        } while (userSelectionAdmin != 4);

    }
    

}
