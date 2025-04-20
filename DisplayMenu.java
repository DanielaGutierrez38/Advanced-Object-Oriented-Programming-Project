import java.util.Scanner;

public class DisplayMenu {   
    private MissionControl missionControl = new MissionControl();

    public void displayMainMenu(){

        //int userSelection = 1;

        //do{

            System.out.println("------------------Space Debris in LEO Tracker------------------");
            System.out.println("Welcome! Please select the number corresponding to your user type");
            System.out.println("1-. Scientist");
            System.out.println("2-. Space Agency Representative");
            System.out.println("3-. Policymaker");
            System.out.println("4-. Administrator");
            System.out.println("5-. Exit");

            Scanner input = new Scanner(System.in);
            int userSelection = input.nextInt();
            Logger newLogger = new Logger();

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
                    System.out.println("Thank you for using the system! See you next time :)");

                break;

                default:
                    System.out.println("Invalid input. Please select a number between 1 and 4");
                break;

            }//end switch

        //}while (userSelection != 5);//end while


    }//end displayMainMenu

public void displayScientistMenu() {
    Scanner inputScientist = new Scanner(System.in);
    int userSelectionScientist;

    do {
        System.out.println("........................User: Scientist........................");
        System.out.println("Please select the number for the action that you want to perform");
        System.out.println("1-. Track Objects in Space");
        System.out.println("2-. Assess Orbit Status");
        System.out.println("3-. Back");

        userSelectionScientist = inputScientist.nextInt();

        switch (userSelectionScientist) {
            case 1:
                System.out.println("Please select the type of object that you want to track: ");
                System.out.println("1-. Rocket Body");
                System.out.println("2-. Debris");
                System.out.println("3-. Payload");
                System.out.println("4-. Unknown");

                int objectTrack = inputScientist.nextInt();

                if (objectTrack >= 1 && objectTrack <= 4) {
                    missionControl.trackObjectsInSpace(objectTrack); // passes choice
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 4");
                }
                break;

            case 2:
                System.out.println("Please select the action that you want to perform");
                System.out.println("1-. Track Objects in LEO");
                System.out.println("2-. Assess if debris is still in orbit");

                int orbitStatus = inputScientist.nextInt();

                switch (orbitStatus) {
                    case 1:
                        // implement LEO tracking
                        break;
                    case 2:
                        // implement orbit assessment
                        break;
                    default:
                        System.out.println("Invalid input. Please select a number between 1 and 2");
                        break;
                }
                break;

            case 3:
                Logger newLogger = new Logger();
                newLogger.logScientistExit();
                displayMainMenu(); // Go back to main menu
                break;

            default:
                System.out.println("Invalid input. Please enter a number between 1 and 3");
                break;
        }

    } while (userSelectionScientist != 3);
}

    public void displaySpaceAgentRepMenu(){

        System.out.println("...............User: Space Agency Representative...............");
        System.out.println("Please select the number for the action that you want to perform");
        System.out.println("1-. Analyze long-term Impact");
        System.out.println("2-. Generate Density Reports");
        System.out.println("3-. Back");

        Scanner inputSAR = new Scanner(System.in);
        int userSelectionSAR = inputSAR.nextInt();
        Logger spaceAgentLogger = new Logger();

        if (userSelectionSAR == 3) {
            //inputSAR.close();
            spaceAgentLogger.logSpaceAgentRepExit();
            displayMainMenu();
        }

    }

    public void displayPolicymakerMenu(){

        System.out.println(".......................User: Policymaker.......................");
        System.out.println("Please select the number for the action that you want to perform");
        System.out.println("1-. Review Reports on Debris Impact");
        System.out.println("2-. Assess Risk Levels for Future Space Missions");
        System.out.println("3-. Back");

        Scanner inputPM = new Scanner(System.in);
        int userSelectionPM = inputPM.nextInt();
        Logger policymakerLogger = new Logger();

        if (userSelectionPM == 3) {
            //inputPM.close();
            policymakerLogger.logPolicymakerExit();
            displayMainMenu();
        }

    }

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

        if (userSelectionAdmin == 4) {
            //inputAdmin.close();
            administratorLogger.logAdministratorExit();
            displayMainMenu();
        }

    }

}
