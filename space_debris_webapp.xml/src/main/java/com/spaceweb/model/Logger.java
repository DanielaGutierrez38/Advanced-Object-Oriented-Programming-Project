package com.spaceweb.model;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is responsible for logging all queries, user interactions and updates on the system.
 * Implements Singleton design pattern to ensure only one instance is used throughout the system.
 * 
 * @author Daniela Gutierrez
 * @author Caitlin Gregory
 */
public class Logger{
    // 1. Private static instance
    // Only instance of Logger
    private static Logger instance;

    // 2. Private constructor
    // prevents other classes from creating a new logger
    private Logger(){}

    // 3. Public static method to get the single instance
        // Controls access to the only instance.
        // The first time it’s called, it creates the instance.
        // On every later call, it just returns the already-created one.
    public static Logger getInstance(){
        if (instance == null){
            instance = new Logger();
        }
        return instance;
    }

    // Logging print method
    public void log(String message){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        String appendText = "\n" + formattedDate + " " + message;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt", true))){
            writer.write(appendText);
        } catch (IOException ioe){
            System.out.println("Couldn't write to file");
        }
    }
}
