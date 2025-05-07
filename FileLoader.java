import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Responsible for loading the file and initiating the parsing process.
 * Handles BOM and builds column header map for further parsing.
 * 
 * @author Caitlin Gregory
 * @author Daniela Gutierrez
 */
public class FileLoader{

    /** Default constructor */
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
            String headerLine = br.readLine(); // read header line
            if (headerLine != null){
                // Remove BOM (Byte Order Mark) from first line
                headerLine = headerLine.replace("\uFEFF", "");

                String[] headers = headerLine.split(",");
                for (int i = 0; i < headers.length; i++){
                    String normalizedHeader = headers[i].trim().toLowerCase();
                    columnIndex.put(normalizedHeader, i);
                }
            }

            String line;
            while ((line = br.readLine()) != null){
                rawLines.add(line);
            }

        } catch (IOException e){
            System.out.println("Error reading file: " + e.getMessage());
        }

        return ArrayBuilder.toArrayAndForward(rawLines, columnIndex);
    }
}
