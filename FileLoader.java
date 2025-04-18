import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Responsible for loading the file and initiating the parsing process.
 */
public class FileLoader {

    /**
     * Loads the file and returns a Map of space objects.
     * 
     * @param filePath the path to the CSV file
     * @return a Map of SpaceObjects indexed by their record ID
     */
    public static Map<String, SpaceObject> load(String filePath) {
        List<String> rawLines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip header
            String line;
            while ((line = br.readLine()) != null) {
                rawLines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return ArrayBuilder.toArrayAndForward(rawLines);
    }
}
