import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
/**
 * Responsible for loading the file and initiating the parsing process.
 * @author Caitlin Gregory
 * @author Daniela Gutierrez
 */
public class FileLoader {

    /**Default constructor */
    FileLoader(){}

    /**
     * Loads the file and returns a Map of space objects.
     * 
     * @param filePath the path to the CSV file
     * @return a Map of SpaceObjects indexed by their record ID
     */
    public static Map<String, SpaceObject> load(String filePath){
        List<String> rawLines = new ArrayList<>();
        Map<String, Integer> columnIndex = new LinkedHashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String headerLine = br.readLine(); // read header
            if (headerLine != null){
                String[] headers = headerLine.split(",");
                for (int i = 0; i < headers.length; i++){
                    columnIndex.put(headers[i].trim().toLowerCase(), i); // normalize headers (lowercase, trim spaces)
                }
            }

            String line;
            while ((line = br.readLine()) != null){
                rawLines.add(line);
            }
        } catch (IOException e){
            System.out.println("Error reading file: " + e.getMessage());
        }

        return ArrayBuilder.toArrayAndForward(rawLines, columnIndex);  // pass to an array
    }
}
