/**
 * Class that represents a Space Agent Representative user. Child class of User
 * 
 * @author Caitlin Gregory
 * @author Daniela Gutierrez
 */
class SpaceAgentRep extends User{

    /**Default Constructor */
    SpaceAgentRep(){}
    
    public SpaceAgentRep(String username, String hash, String salt) {
        super(username, hash, salt);
    }

    /**Method that gets the user type (override) 
     * @return string with user type
    */
    @Override
    public String getUserType() {
        return "SpaceAgentRep";
    }

}
