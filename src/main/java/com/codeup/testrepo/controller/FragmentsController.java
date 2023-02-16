package com.codeup.testrepo.controller;

import com.codeup.testrepo.models.Roles;
import com.codeup.testrepo.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.codeup.testrepo.models.Listings;
import com.codeup.testrepo.models.Roles;
import com.codeup.testrepo.repositories.RolesRepository;
import com.codeup.testrepo.repositories.UserRepository;

import com.codeup.testrepo.models.User;
import com.codeup.testrepo.repositories.ListingRepository;
import com.codeup.testrepo.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.mapping.List;
import org.springframework.context.annotation.Conditional;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Objects;

@Controller
public class FragmentsController {


    @GetMapping("/profile/{userId}")
    public String showProfilePage(@PathVariable Long userId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || !user.getId().equals(userId)) {
            // The user is not authorized to view this profile
            return "redirect:/";
        }
        model.addAttribute("user", user);
        // Add other profile information as needed
        return "profile";
    }

    @GetMapping("/about-us")
    public String aboutUs() {
//        model.addAttribute("new-listing-card", new CreateListing());
        return "users/about-us";
    }
    @GetMapping("/contact-us")
    public String contactUs() {
//        model.addAttribute("new-listing-card", new CreateListing());
        return "users/contact-us";
    }


}
