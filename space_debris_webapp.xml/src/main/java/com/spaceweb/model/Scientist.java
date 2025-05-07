package com.spaceweb.model;

public class Scientist extends User {

    public Scientist(String username, String password, String role) {
        super(username, password, role);
    }

    public void performScientificAnalysis() {
        System.out.println("Performing analysis...");
    }
}
