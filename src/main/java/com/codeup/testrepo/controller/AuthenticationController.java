package com.codeup.testrepo.controller;

// This package contains the controllers for the testrepo application.

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// This annotation marks this class as a Spring MVC controller.

@Controller
public class AuthenticationController {

// This class handles requests for the authentication controller.

    @GetMapping("/login")
    public String showLoginForm() {
        // This method returns the "users/login" view, which renders the login form.
        return "users/login";
    }

}
