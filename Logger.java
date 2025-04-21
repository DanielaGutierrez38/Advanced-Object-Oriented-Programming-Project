import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is responsible for logging all queries, user interactions and updates on the system
 * @author Daniela Gutierrez
 * @author Caitlin Gregory
 */
class Logger extends MissionControl{

    /**Default constructor */
    Logger(){}

    /**
     * Method that appends a Scientist log in to the logger.txt file
     */
    public void logScientistIn() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        //System.out.println(formattedDate);

        String appendText = "\n" + formattedDate + " Scientist User logged in";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt", true));
            writer.write(appendText);

            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }

    }//end Scientist log in

    /**
     * Method that appends a Space Agent Representative log in to the logger.txt file
     */
    public void logSpaceAgentRepIn() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        //System.out.println(formattedDate);

        String appendText = "\n" + formattedDate + " Space Agent Representative User logged in";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt", true));
            writer.write(appendText);

            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }

    }//end space agent rep log in

    /**
     * Method that appends a Policymaker log in to the logger.txt file
     */
    public void logPolicymakerIn() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        //System.out.println(formattedDate);

        String appendText = "\n" + formattedDate + " Policymaker User logged in";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt", true));
            writer.write(appendText);

            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }

    }//end policymaker log in 

    /**
     * Method that appends an Administrator log in to the logger.txt file
     */
    public void logAdministratorIn() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        //System.out.println(formattedDate);

        String appendText = "\n" + formattedDate + " Administrator User logged in";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt", true));
            writer.write(appendText);

            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }

    }//end administrator log in

    /**
     * Method that appends a Scientist log out to the logger.txt file
     */
    public void logScientistExit() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        //System.out.println(formattedDate);

        String appendText = "\n" + formattedDate + " Scientist User logged out";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt", true));
            writer.write(appendText);

            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }

    }//end ScientistExit

    /**
     * Method that appends a Space Agent Representative log out to the logger.txt file
     */
    public void logSpaceAgentRepExit() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        //System.out.println(formattedDate);

        String appendText = "\n" + formattedDate + " Space Agent Representative User logged out";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt", true));
            writer.write(appendText);

            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }

    }//end Space Agent Rep exit

    /**
     * Method that appends a Policymaker log out to the logger.txt file
     */
    public void logPolicymakerExit() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        //System.out.println(formattedDate);

        String appendText = "\n" + formattedDate + " Policymaker User logged out";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt", true));
            writer.write(appendText);

            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }

    }//end PolicymakerExit

    /**
     * Method that appends an Administrator log out to the logger.txt file
     */
    public void logAdministratorExit() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        //System.out.println(formattedDate);

        String appendText = "\n" + formattedDate + " Administrator User logged out";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt", true));
            writer.write(appendText);

            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }

    }//end Admin exit

    /**
     * Method that appends a Rocket Body query to the logger.txt file
     */
    public void logQueryRocketBody() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        //System.out.println(formattedDate);

        String appendText = "\n" + formattedDate + " Scientist queried Rocket Body objects.";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt", true));
            writer.write(appendText);

            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }

    }

    /**
     * Method that appends a Debris query to the logger.txt file
     */
    public void logQueryDebris() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        //System.out.println(formattedDate);

        String appendText = "\n" + formattedDate + " Scientist queried Debris objects.";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt", true));
            writer.write(appendText);

            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }

    }

    /**
     * Method that appends a Payload query to the logger.txt file
     */
    public void logQueryPayload() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        //System.out.println(formattedDate);

        String appendText = "\n" + formattedDate + " Scientist queried Payload Body objects.";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt", true));
            writer.write(appendText);

            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }

    }

    /**
     * Method that appends an unknown object query to the logger.txt file
     */
    public void logQueryUnknown() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        //System.out.println(formattedDate);

        String appendText = "\n" + formattedDate + " Scientist queried Unknown objects.";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt", true));
            writer.write(appendText);

            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }

    }

    /**
     * Method that appends a LEO Object query to the logger.txt file
     */
    public void logQueryTrackLEOObjects() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        //System.out.println(formattedDate);

        String appendText = "\n" + formattedDate + " Scientist queried LEO Objects";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt", true));
            writer.write(appendText);

            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }

    }

    /**
     * Method that appends an update to CSV file with debris orbit status to the logger.txt file
     */
    public void updateCSVFile() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        //System.out.println(formattedDate);

        String appendText = "\n" + formattedDate + " CSV File was updated with debris orbit status";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt", true));
            writer.write(appendText);

            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }

    }

    /**
     * Method that appends a debris orbit status assessment to the logger.txt file
     */
    public void logDebrisOrbitStatus() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        //System.out.println(formattedDate);

        String appendText = "\n" + formattedDate + " Scientist assessed orbit status of Debris";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt", true));
            writer.write(appendText);

            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }

    }

    /**
     * Method that appends the creation of the txt file with debris count to the logger.txt file
     */
    public void logCreateExitedDebrisTxtFile() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        //System.out.println(formattedDate);

        String appendText = "\n" + formattedDate + " TXT file was created with inorbit and exited debris count";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt", true));
            writer.write(appendText);

            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }

    }
    
}
