import java.util.Scanner;

public class DisplayMenu {
    
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

    public void displayScientistMenu(){

        System.out.println("........................User: Scientist........................");
        System.out.println("Please select the number for the action that you want to perform");
        System.out.println("1-. Track Objects in Space");
        System.out.println("2-. Assess Orbit Status");
        System.out.println("3-. Back");

        Scanner inputScientist = new Scanner(System.in);
        int userSelectionScientist = inputScientist.nextInt();

        switch (userSelectionScientist){

            case 1:

                System.out.println("Please select the type of object that you want to track: ");
                System.out.println("1-. Rocket Body");
                System.out.println("2-. Debris");
                System.out.println("3-. Payload");
                System.out.println("4-. Unknown");
                int objectTrack = inputScientist.nextInt();

                switch(objectTrack) {

                    case 1:
                        //call method to print rocket bodies
                    break;

                    case 2:
                        //call method to print debris
                    break;

                    case 3:
                        //call method to print payload
                    break;

                    case 4:
                        //call method to print unknown
                    break;

                    default:
                        System.out.println("Invalid input. Please enter a number between 1 and 4");
                    break;

                }//end objectTrack

            break; //end case 1

            case 2:
                System.out.println("Please select the action that you want to perform");
                System.out.println("1-. Track Objects in LEO");
                System.out.println("2-. Assess if debris is still in orbit");
                int orbitStatus = inputScientist.nextInt();

                switch(orbitStatus) {

                    case 1:
                        //display list of all objects in the LEO and their info
                        Scientist newScientist = new Scientist();
                        newScientist.trackObjectsInLEO();
                    break;

                    case 2:
                        //do calculations and update files
                    break;

                    default:
                        System.out.println("Invalid input. Please select a number between 1 and 2");
                    break;

                }
            break;

            case 3:
                Logger newLogger = new Logger();
                newLogger.logScientistExit();
                //inputScientist.close();
                displayMainMenu();
            break;

            default:
                System.out.println("Invalid input. Please enter a number between 1 and 3");
            break;

        }


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
