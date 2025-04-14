import java.util.Scanner;

class RunSimulation{

    public static void main(String[] args){

        System.out.println("------------------Space Debris in LEO Tracker------------------");
        System.out.println("Welcome! Please select your user type");
        System.out.println("1-. Scientist");
        System.out.println("2-. Space Agency Representative");
        System.out.println("3-. Policymaker");
        System.out.println("4-. Administrator");
        System.out.println("5-. Exit");

        Scanner input = new Scanner(System.in);
        int userSelection = input.nextInt();

        switch(userSelection){

            case 1:  

                System.out.println("........................User: Scientist........................");
                System.out.println("Please select the action that you want to perform");
                System.out.println("1-. Track Objects in Space");
                System.out.println("2-. Assess Orbit Status");
                System.out.println("3-. Back"); //add logic to go back to space debris in leo tracker
                int scientistChoice = input.nextInt();

                switch(scientistChoice) {

                    case 1:

                        System.out.println("Please select the type of object that you want to track: ");
                        System.out.println("1-. Rocket Body");
                        System.out.println("2-. Debris");
                        System.out.println("3-. Payload");
                        System.out.println("4-. Unknown");
                        int objectTrack = input.nextInt();

                        switch(objectTrack) {

                            case 1:
                                //display info for all rocket body objects
                            break;

                            case 2:
                                //display info for all debris
                            break;

                            case 3:
                                //display info for all payload
                            break;

                            case 4:
                                //display info for all unknown
                            break;

                            default:
                                System.out.println("Invalid input. Please select a number between 1 and 4");
                            break;

                        }

                    break;

                    case 2:

                        System.out.println("Please select the action that you want to perform");
                        System.out.println("1-. Track Objects in LEO");
                        System.out.println("2-. Assess if debris is still in orbit");
                        int orbitStatus = input.nextInt();

                        switch(orbitStatus) {

                            case 1:
                                //display list of all objects in the LEO and their info
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
                        //go back 
                    break;

                    default:
                        System.out.println("Invalid input. Please select a number between 1 and 3");
                    break;

                }

            break;

            case 2:
                System.out.println("...............User: Space Agency Representative...............");
                System.out.println("Please select the action that you want to perform");
                System.out.println("1-. Analyze long-term Impact");
                System.out.println("2-. Generate Density Reports");
                System.out.println("3-. Back"); //add logic to go back to space debris in leo tracker

            break;

            case 3:
                System.out.println(".......................User: Policymaker.......................");
                System.out.println("Please select the action that you want to perform");
                System.out.println("1-. Review Reports on Debris Impact");
                System.out.println("2-. Assess Risk Levels for Future Space Missions");
                System.out.println("3-. Back"); //add logic to go back to space debris in leo tracker

            break;

            case 4:
                System.out.println("......................User: Administrator......................");
                System.out.println("Please select the action that you want to perform");
                System.out.println("1-. Create User");
                System.out.println("2-. Manage User");
                System.out.println("3-. Delete User"); 
                System.out.println("4-. Back"); //add logic to go back to space debris in leo tracker

            break;

            case 5:
                System.out.println("Thank you for using the system! See you next time :)");
            break;

            default:
                System.out.println("Invalid input. Please select a number between 1 and 5");
            break;

        }

        input.close();

    }

}