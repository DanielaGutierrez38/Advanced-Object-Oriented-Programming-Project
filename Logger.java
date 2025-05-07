import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is responsible for logging all queries, user interactions and updates on the system.
 * Implements the Singleton design pattern to ensure only one instance is used throughout the system.
 * 
 * All log entries are timestamped and appended to a log file named "logger.txt".
 * 
 * @author Daniela Gutierrez
 * @author Caitlin Gregory
 */
public class Logger {

    /**Private static instance, Only instance of Logger*/
    private static Logger instance;

    /** Prevents other classes from creating a new logger directly */
    private Logger() {}

    /**
     * Returns the single instance of Logger.
     * Creates the instance if it does not already exist.
     *
     * @return the singleton Logger instance
     */
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    /**
     * Logs a message with the current date and time to a file called "logger.txt".
     * Each message is written on a new line and timestamped for tracking.
     *
     * @param message the message to be logged
     */
    public void log(String message) {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        String appendText = "\n" + formattedDate + " " + message;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt", true))) {
            writer.write(appendText);
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }
    }
}
