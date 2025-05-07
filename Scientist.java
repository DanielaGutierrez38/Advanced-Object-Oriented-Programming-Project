//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.lang.Math;

/**
 * Class that represents a Scientist User. Child class of user
 * @author Caitlin Gregory
 * @author Daniela gutierrez
 */
class Scientist extends User{

    /**Default constructor */
    Scientist(){}

    /**Constructor to initialize Scientist user  with username, hash and salt*/
    public Scientist(String username, String hash, String salt) {
        super(username, hash, salt);
    }

    /**Method that gets the user type (override) 
     * @return string with user type
    */
    @Override
    public String getUserType() {
        return "Scientist";
    }

}





