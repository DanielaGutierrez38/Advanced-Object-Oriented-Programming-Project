package com.spaceweb.model;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private Map<String, User> users = new HashMap<>();
    private String filePath;

    public UserManager(String filePath) {
        this.filePath = filePath;
        loadUsers();
    }

    public boolean authenticate(String username, String password, String role) {
        User user = users.get(username);
        return user != null && user.getPassword().equals(password) && user.getRole().equals(role);
    }

    public boolean createUser(String username, String password, String role) {
        if (users.containsKey(username)) {
            return false;
        }
        users.put(username, new BaseUser(username, password, role));
        saveUsers();
        return true;
    }

    private void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.strip().split(",");
                if (parts.length >= 3) {
                    String username = parts[0];
                    String password = parts[1];
                    String role = parts[2];
                    users.put(username, new BaseUser(username, password, role));
                }
            }
        } catch (IOException e) {
            System.out.println("No existing user data found.");
        }
    }

    private void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (User user : users.values()) {
                writer.write(user.getUsername() + "," + user.getPassword() + "," + user.getRole());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to save users: " + e.getMessage());
        }
    }
}
