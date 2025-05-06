/**
 * Class that represents the Administrator user. Child class of User
 * 
 * @author Caitlin Gregory
 * @author Daniela Gutierrez
 */
public class Administrator extends User {
    private final UserManager userManager;

    public Administrator(String username, String hash, String salt, UserManager userManager) {
        super(username, hash, salt);
        this.userManager = userManager;
    }

    @Override
    public String getUserType() {
        return "Administrator";
    }

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

    public void deleteUser(String username) {
        userManager.deleteUser(username);
    }

    public void updateUserFlexible(String oldUsername, String newUsername, String newPassword) {
        userManager.updateUserFlexible(oldUsername, newUsername, newPassword);
    }

    public User getUser(String username) {
        return userManager.getUser(username);
    }

}
