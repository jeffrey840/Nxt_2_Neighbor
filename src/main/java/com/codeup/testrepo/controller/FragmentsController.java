package com.codeup.testrepo.controller;

import ch.qos.logback.core.model.Model;
import com.codeup.testrepo.models.User;
import com.codeup.testrepo.models.CreateListing;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FragmentsController {

    @GetMapping("/logged-in-navbar")
    public String getProfile(HttpServletRequest request) {
        User user =(User) request.getSession().getAttribute("user");
        if(user.getRole().getId() == 1){
            return "redirect:/buyer-profile.html";
        } else if(user.getRole().getId() == 2) {
            return "redirect:/seller-profile.html";
        } else if(user.getRole().getId() == 3) {
            return "redirect:/neighbor-profile.html";
        } else {
            return "redirect:/error";
        }
    }

//    @GetMapping("/card")
//    public String showCard(Model model) {
//        model.addAttribute("new-listing-card", new CreateListing());
//        return "new-listing-card";
//    }
//    @PostMapping("/card")
//    public String updateCard(@ModelAttribute CreateListing card, Model model) {
//        model.addAttribute("new-listing-card", card);
//        return "new-listing-card";
//    }
}
