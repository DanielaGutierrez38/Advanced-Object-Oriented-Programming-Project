import java.util.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * Manages user accounts including creation, deletion, update, authentication,
 * and persistent storage to a CSV file. Supports user types such as Scientist,
 * Administrator, and SpaceAgentRep.
 * 
 * Provides functionality for salting, hashing passwords, and verifying login credentials.
 * Also acts as a factory for loading users from a CSV file and saving them back.
 * 
 * @author Caitlin Gregory
 * @author Daniela Gutierrez
 */
public class UserManager {
    /** Stores all user accounts keyed by username */
    private Map<String, User> userMap = new HashMap<>();

    /** Path to the CSV file where user data is stored */
    private final String filePath;

    /**
     * Constructs a UserManager with the specified CSV file path and loads existing users.
     * 
     * @param filePath path to the CSV file containing user records
     */
    public UserManager(String filePath) {
        this.filePath = filePath;
        loadUsersFromCSV();
    }

    /**
     * Adds a new user to the system and saves it to the CSV file.
     * If the user already exists, it prints an error and does not overwrite.
     * 
     * @param user the User object to add
     */
    public void addUser(User user) {
        if (userMap.containsKey(user.getUsername())) {
            System.out.println("User already exists.");
            return;
        }
        userMap.put(user.getUsername(), user);
        saveUsersToCSV();
        System.out.println("User created successfully.");
    }

    /**
     * Deletes a user by username. If the user exists, they are removed and changes are saved.
     * 
     * @param username the username of the user to delete
     */
    public void deleteUser(String username) {
        if (userMap.remove(username) != null) {
            saveUsersToCSV();
            System.out.println("User deleted.");
        } else {
            System.out.println("User not found.");
        }
    }

    /**
     * Updates a user’s username and/or password. If the password is "[KEEP_OLD_PASSWORD]",
     * the current password is retained.
     * 
     * @param oldUsername the existing username
     * @param newUsername the new username to set
     * @param newPassword the new password to set or "[KEEP_OLD_PASSWORD]" to retain the current one
     */
    public void updateUserFlexible(String oldUsername, String newUsername, String newPassword) {
        User user = userMap.get(oldUsername);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        String newSalt = generateSalt();
        String newHash;

        if (newPassword.equals("[KEEP_OLD_PASSWORD]")) {
            newHash = user.getPasswordHash();
            newSalt = user.getSalt();
        } else {
            newHash = hashPassword(newPassword, newSalt);
        }

        User updatedUser;
        switch (user.getUserType()) {
            case "Scientist":
                updatedUser = new Scientist(newUsername, newHash, newSalt); break;
            case "SpaceAgentRep":
                updatedUser = new SpaceAgentRep(newUsername, newHash, newSalt); break;
            case "Administrator":
                updatedUser = new Administrator(newUsername, newHash, newSalt, this); break;
            default:
                System.out.println("Unknown user type.");
                return;
        }

        userMap.remove(oldUsername);
        userMap.put(newUsername, updatedUser);
        saveUsersToCSV();
        System.out.println("User updated.");
    }

    /**
     * Retrieves a user by their username.
     * 
     * @param username the username of the user
     * @return the User object, or null if not found
     */
    public User getUser(String username) {
        return userMap.get(username);
    }

    /**
     * Retrieves all user objects in the system.
     * 
     * @return a Collection of all users
     */
    public Collection<User> getAllUsers() {
        return userMap.values();
    }

    /**
     * Generates a new cryptographically secure random salt encoded in Base64.
     * 
     * @return the generated salt string
     */
    public String generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    /**
     * Hashes a password using SHA-256 and a salt.
     * 
     * @param password the plaintext password
     * @param salt the salt to prepend to the password
     * @return the Base64-encoded hashed password
     */
    public String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update((salt + password).getBytes());
            byte[] digest = md.digest();
            return Base64.getEncoder().encodeToString(digest);
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    /**
     * Authenticates a user by comparing hashed credentials and checking the user role.
     * 
     * @param username the entered username
     * @param password the entered plaintext password
     * @param role the expected role of the user
     * @return true if credentials and role match; false otherwise
     */
    public boolean authenticateUser(String username, String password, String role) {
        User user = userMap.get(username);
        if (user == null) return false;
        String hashedAttempt = hashPassword(password, user.getSalt());
        return user.getPasswordHash().equals(hashedAttempt) && user.getUserType().equals(role);
    }

    /**
     * Loads all user records from the CSV file and constructs user objects based on type.
     * Expects each line to have 4 comma-separated values: username, hash, salt, type.
     */
    private void loadUsersFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String username = parts[0], hash = parts[1], salt = parts[2], type = parts[3];
                    switch (type) {
                        case "Scientist": userMap.put(username, new Scientist(username, hash, salt)); break;
                        case "Administrator": userMap.put(username, new Administrator(username, hash, salt, this)); break;
                        case "SpaceAgentRep": userMap.put(username, new SpaceAgentRep(username, hash, salt)); break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to load users: " + e.getMessage());
        }
    }

    /**
     * Saves all current users to the CSV file in the format:
     * username,passwordHash,salt,userType
     */
    private void saveUsersToCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (User user : userMap.values()) {
                bw.write(user.getUsername() + "," + user.getPasswordHash() + "," + user.getSalt() + "," + user.getUserType() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Failed to save users: " + e.getMessage());
        }
    }
}
