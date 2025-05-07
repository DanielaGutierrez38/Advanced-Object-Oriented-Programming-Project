package com.spaceweb.controller;

import com.spaceweb.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    private SpaceService spaceService;

    @GetMapping("/")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username,
                              @RequestParam String password,
                              @RequestParam String role,
                              @RequestParam String action,
                              Model model) {
        if ("create".equals(action)) {
            boolean created = spaceService.createUser(username, password, role);
            if (created) {
                model.addAttribute("username", username);
                model.addAttribute("role", role);
                return getMenuForRole(role);
            } else {
                model.addAttribute("error", "Username already exists.");
                return "login";
            }
        } else {
            boolean authenticated = spaceService.authenticate(username, password, role);
            if (authenticated) {
                model.addAttribute("username", username);
                model.addAttribute("role", role);
                return getMenuForRole(role);
            } else {
                model.addAttribute("error", "Invalid credentials.");
                return "login";
            }
        }
    }

    private String getMenuForRole(String role) {
        switch (role.toLowerCase()) {
            case "scientist":
                return "scientist-menu";
            case "space agency rep":
                return "space-rep-menu";
            case "administrator":
                return "admin-menu";
            default:
                return "login";
        }
    }

    @PostMapping("/track-objects")
    public String trackObjects(Model model) {
        String result = spaceService.trackObjects(model);
        model.addAttribute("result", result);
        return "result";
    }

    @PostMapping("/assess-orbit")
    public String assessOrbit(Model model) {
        String result = spaceService.assessOrbitStatus(model);
        model.addAttribute("result", result);
        return "result";
    }

    @PostMapping("/analyze-impact")
    public String analyzeImpact(Model model) {
        String result = spaceService.runLongTermImpactAnalysis();
        model.addAttribute("result", result);
        return "result";
    }

    @PostMapping("/density-report")
    public String densityReport(Model model) {
        String result = spaceService.generateDensityReport(model);
        model.addAttribute("result", result);
        return "result";
    }
}
