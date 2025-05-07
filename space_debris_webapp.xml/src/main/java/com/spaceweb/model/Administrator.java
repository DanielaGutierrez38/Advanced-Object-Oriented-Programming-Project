package com.spaceweb.model;

public class Administrator extends User {

    private UserManager userManager;

    public Administrator(String username, String password, String role, UserManager userManager) {
        super(username, password, role);
        this.userManager = userManager;
    }

    public void createUser(String username, String password, String role) {
        // Removed salt/hash logic for simplicity
        userManager.createUser(username, password, role);
    }

    // The following methods are commented out because UserManager no longer supports them
    /*
    public void deleteUser(String username) {
        userManager.deleteUser(username);
    }

    public void updateUserFlexible(String username, String newPassword, String newRole) {
        userManager.updateUserFlexible(username, newPassword, newRole);
    }

    public User getUser(String username) {
        return userManager.getUser(username);
    }
    */
}
