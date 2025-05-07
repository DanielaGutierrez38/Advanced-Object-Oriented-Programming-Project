/**
 * Class that represents the Administrator user. Child class of User.
 * The Administrator has privileges to manage users (create, update, delete).
 * 
 * @author Caitlin Gregory
 * @author Daniela Gutierrez
 */
public class Administrator extends User {

    /** Reference to the UserManager for handling user operations */
    private final UserManager userManager;

    /**
     * Constructor to create an Administrator object.
     *
     * @param username the administrator's username
     * @param hash the hashed password
     * @param salt the salt used in hashing
     * @param userManager reference to the user manager to perform user operations
     */
    public Administrator(String username, String hash, String salt, UserManager userManager) {
        super(username, hash, salt);
        this.userManager = userManager;
    }

    /**
     * Gets the type of this user.
     *
     * @return the string "Administrator"
     */
    @Override
    public String getUserType() {
        return "Administrator";
    }

    /**
     * Creates a new user of the given type and adds them to the system.
     *
     * @param username the new user's username
     * @param password the new user's plaintext password
     * @param type the type of user ("Scientist", "SpaceAgentRep", or "Administrator")
     */
    public void createUser(String username, String password, String type) {
        String salt = userManager.generateSalt();
        String hash = userManager.hashPassword(password, salt);

        User newUser = switch (type) {
            case "Scientist" -> new Scientist(username, hash, salt);
            case "SpaceAgentRep" -> new SpaceAgentRep(username, hash, salt);
            case "Administrator" -> new Administrator(username, hash, salt, userManager);
            default -> null;
        };

        if (newUser != null) {
            userManager.addUser(newUser);
        } else {
            System.out.println("Unknown user type.");
        }
    }

    /**
     * Deletes a user by their username.
     *
     * @param username the username of the user to delete
     */
    public void deleteUser(String username) {
        userManager.deleteUser(username);
    }

    /**
     * Updates an existing user's username and/or password.
     * If the password is "[KEEP_OLD_PASSWORD]", the password remains unchanged.
     *
     * @param oldUsername the current username
     * @param newUsername the new username
     * @param newPassword the new password or "[KEEP_OLD_PASSWORD]" to leave unchanged
     */
    public void updateUserFlexible(String oldUsername, String newUsername, String newPassword) {
        userManager.updateUserFlexible(oldUsername, newUsername, newPassword);
    }

    /**
     * Retrieves a user by username.
     *
     * @param username the username of the user to retrieve
     * @return the corresponding User object, or null if not found
     */
    public User getUser(String username) {
        return userManager.getUser(username);
    }

}
