package com.spaceweb.model;

public class SpaceAgentRep extends User {

    public SpaceAgentRep(String username, String password, String role) {
        super(username, password, role);
    }

    public void generateReports() {
        System.out.println("Generating space debris report...");
    }
}
