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
     * Method that appends an action to the logger.txt file
     */
    public void log(String message) {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        String appendText = "\n" + formattedDate + message;

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt", true));
            writer.write(appendText);

            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }

    }
    
}
