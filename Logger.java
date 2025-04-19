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

    /**
     * Method that appends a Scientist log in to the logger.txt file
     * @param None
     * @return void
     */
    public void logScientistIn() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        System.out.println(formattedDate);

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
     * @param None
     * @return void
     */
    public void logSpaceAgentRepIn() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        System.out.println(formattedDate);

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
     * @param None
     * @return void
     */
    public void logPolicymakerIn() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        System.out.println(formattedDate);

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
     * @param None
     * @return void
     */
    public void logAdministratorIn() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        System.out.println(formattedDate);

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
     * @param None
     * @return void
     */
    public void logScientistExit() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        System.out.println(formattedDate);

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
     * Method that appends a Space Agent R to the logger.txt file
     * @param None
     * @return void
     */
    public void logSpaceAgentRepExit() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        System.out.println(formattedDate);

        String appendText = "\n" + formattedDate + " Space Agent Representative User logged out";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt", true));
            writer.write(appendText);

            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }

    }//end Space Agent Rep exit

    public void logPolicymakerExit() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        System.out.println(formattedDate);

        String appendText = "\n" + formattedDate + " Policymaker User logged out";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt", true));
            writer.write(appendText);

            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }

    }//end PolicymakerExit

    public void logAdministratorExit() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        System.out.println(formattedDate);

        String appendText = "\n" + formattedDate + " Administrator User logged out";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt", true));
            writer.write(appendText);

            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }

    }//end Admin exit


    //////////////////Still changing these (will change logic further once methods implemented)/////////////////////
    public void logQueryRocketBody() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        System.out.println(formattedDate);

        String appendText = "\n" + formattedDate + " Administrator User logged out";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt", true));
            writer.write(appendText);

            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }

    }//end Admin exit

    public void logQueryDebris() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        System.out.println(formattedDate);

        String appendText = "\n" + formattedDate + " Administrator User logged out";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt", true));
            writer.write(appendText);

            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }

    }//end Admin exit

    public void logQueryPayload() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        System.out.println(formattedDate);

        String appendText = "\n" + formattedDate + " Administrator User logged out";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt", true));
            writer.write(appendText);

            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }

    }//end Admin exit

    public void logQueryUnknown() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        System.out.println(formattedDate);

        String appendText = "\n" + formattedDate + " Administrator User logged out";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt", true));
            writer.write(appendText);

            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }

    }//end Admin exit

    public void logQueryTrackLEOObjects() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        System.out.println(formattedDate);

        String appendText = "\n" + formattedDate + " Administrator User logged out";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt", true));
            writer.write(appendText);

            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }

    }//end Admin exit
    
}
