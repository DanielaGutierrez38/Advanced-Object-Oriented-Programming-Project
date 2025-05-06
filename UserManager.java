import java.util.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class UserManager {
    private Map<String, User> userMap = new HashMap<>();
    private final String filePath;

    public UserManager(String filePath) {
        this.filePath = filePath;
        loadUsersFromCSV();
    }

    public void addUser(User user) {
        if (userMap.containsKey(user.getUsername())) {
            System.out.println("User already exists.");
            return;
        }
        userMap.put(user.getUsername(), user);
        saveUsersToCSV();
        System.out.println("User created successfully.");
    }

    public void deleteUser(String username) {
        if (userMap.remove(username) != null) {
            saveUsersToCSV();
            System.out.println("User deleted.");
        } else {
            System.out.println("User not found.");
        }
    }

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

    public User getUser(String username) {
        return userMap.get(username);
    }

    public Collection<User> getAllUsers() {
        return userMap.values();
    }

    public String generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

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

    public boolean authenticateUser(String username, String password, String role) {
        User user = userMap.get(username);
        if (user == null) return false;
        String hashedAttempt = hashPassword(password, user.getSalt());
        return user.getPasswordHash().equals(hashedAttempt) && user.getUserType().equals(role);
    }

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
