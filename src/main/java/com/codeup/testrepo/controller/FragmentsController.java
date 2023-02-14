package com.codeup.testrepo.controller;

import com.codeup.testrepo.models.Roles;
import com.codeup.testrepo.models.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.codeup.testrepo.models.Listings;
import com.codeup.testrepo.models.Roles;
import com.codeup.testrepo.repositories.RolesRepository;
import com.codeup.testrepo.repositories.UserRepository;
import com.codeup.testrepo.services.EmailService;
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

    private final UserRepository userDao;
    private final RolesRepository rolesDao;
    private final ListingRepository listDao;
    private final EmailService emailService;


    private ProductService service;


    public FragmentsController(UserRepository userDao, ListingRepository listDao, EmailService emailService, RolesRepository rolesDao) {
        this.userDao = userDao;
        this.listDao = listDao;
        this.rolesDao = rolesDao;
        this.emailService = emailService;



    }

    @GetMapping("/profile")
    public String nav(Model model) {
//        User user =(User) request.getSession().getAttribute("user");
//        model.addAttribute("Users", userDao.findAll());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        User user1 = userDao.findByUsername(currentUser);
        long id = user1.getId();
        User user = userDao.getReferenceById(id);
        Roles roles1 = rolesDao.getReferenceById(user.getRole().getId());
        String roles = roles1.getUser_role();
        if(Objects.equals(roles, "buyer")){
            return "/listings/buyer-profile";
        } else if (Objects.equals(roles, "seller")) {
            return "/listings/seller-profile";
        } else if (Objects.equals(roles, "neighbor")) {
            return "listings/neighbor-profile";
        }
        return "listings/home-not-logged";
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
