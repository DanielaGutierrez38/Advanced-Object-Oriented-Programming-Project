import java.util.Map;

/**
 * TrackingSystem is the central class that loads and manages space object data.
 * <p>
 * It delegates file reading to FileLoader, array parsing to ArrayBuilder,
 * and dictionary creation to DictionaryBuilder.
 * </p>
 * 
 * <p>This class is used by MissionControl to interact with objects in orbit.</p>
 * 
 * @author Caitlin Gregory
 * @author Daniela Gutierrez
 */

public class TrackingSystem{

    /**
     * A dictionary of space objects (Debris or Satellite) indexed by their record ID.
     */
    private Map<String, SpaceObject> objects;

    /**
     * Default constructor
     * 
     */
    public TrackingSystem(){}

    /**
     * Constructor that loads objects from the given CSV file.
     * 
     * @param filePath path to the CSV file
     */
    public TrackingSystem(String filePath){
        this.objects = FileLoader.load(filePath);
    }

    /**
     * Returns the space object corresponding to the provided record ID.
     * 
     * @param recordId the ID of the object
     * @return the SpaceObject or null if not found
     */
    public SpaceObject getObjectById(String recordId){
        return objects.get(recordId);
    }

    /**
     * Returns the entire space object map.
     * 
     * @return the dictionary of all space objects
     */
    public Map<String, SpaceObject> getAllObjects(){
        return objects;
    }

    /**
     * For testing
     * @param objects object dictionary
     */
    public void setObjects(Map<String, SpaceObject> objects){
        this.objects = objects;
    }

}
