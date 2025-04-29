import java.util.List;
import java.util.Map;

/**
 * Converts raw lines into a 2D array and passes it to the dictionary builder.
 * 
 * @author Caitlin Gregory
 * @author Daniela Gutierrez
 */
public class ArrayBuilder{

    /**Default constructor */
    ArrayBuilder(){}

    /**
     * Converts the raw lines into a 2D String array and forwards to DictionaryBuilder.
     * 
     * @param rawLines List of raw lines from the CSV
     * @return a Map of SpaceObject instances
     */
    public static Map<String, SpaceObject> toArrayAndForward(List<String> rawLines, Map<String, Integer> columnIndex){
        String[][] data = new String[rawLines.size()][];

        for (int i = 0; i < rawLines.size(); i++){
            data[i] = rawLines.get(i).split(",");
        }

        return DictionaryBuilder.buildFromArray(data, columnIndex);  // pass to a dictionary
    }
}
