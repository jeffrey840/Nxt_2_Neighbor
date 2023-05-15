package com.codeup.testrepo.controller;
import com.codeup.testrepo.models.Listings;
import com.codeup.testrepo.models.Roles;
import com.codeup.testrepo.repositories.*;
import com.codeup.testrepo.services.EmailService;
import com.codeup.testrepo.models.User;
import com.codeup.testrepo.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Conditional;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Controller
public class ListingController {

    private final UserRepository userDao;
    private final RolesRepository rolesDao;
    private final ListingRepository listDao;
    private final EmailService emailService;

    private final InterestRepository interestDao;

    private final CategoryRepository categoryDao;

    private ProductService.ListingService listingService;
    private ProductService service;


    // Constructor for dependency injection
    public ListingController(UserRepository userDao, ListingRepository listDao, EmailService emailService, RolesRepository rolesDao, InterestRepository interestDao, CategoryRepository categoryDao) {
        this.userDao = userDao;
        this.listDao = listDao;
        this.rolesDao = rolesDao;
        this.emailService = emailService;
        this.interestDao = interestDao;
        this.categoryDao = categoryDao;
    }
    // Welcome page for non-logged-in users
    @GetMapping("/")
    public String welcomePage() {
        return "listings/home-not-logged";
    }

    // User home page based on role
    @GetMapping ("/listings")
    public String userHome(Model model) {
        model.addAttribute("Users", userDao.findAll());
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUsername = currentUser.getUsername();
        User user1 = userDao.findByUsername(currentUsername);
        long id = user1.getId();
        User user = userDao.getReferenceById(id);
        Roles roles1 = rolesDao.getReferenceById(user.getRole().getId());
        String roles = roles1.getUser_role();
        StringBuilder interestsList = new StringBuilder();
        interestsList.append("<div>");
        user.getCategories().forEach(category -> {
            category.getInterests().forEach(interest -> {
                interestsList.append(interest.getName()).append("</div> <div>");
            });
        });
        interestsList.append("</div>");
        model.addAttribute("interestList", interestsList.toString());
        // Determine the user's role and display the appropriate view
        if(Objects.equals(roles, "buyer")){
            model.addAttribute("user", user);
            List<Listings> listings = listDao.findAllByUser(user);
            model.addAttribute("listings", listings);
            return "listings/buyer-profile";
        } else if (Objects.equals(roles, "seller")) {
            model.addAttribute("user", user);
            List<Listings> listings = listDao.findAllByUser(user);
            model.addAttribute("listings", listings);
            return "listings/seller-profile";
        } else if (Objects.equals(roles, "neighbor")) {
            model.addAttribute("user", user);
            return "listings/neighbor-profile";
        }
        return "listings/home-not-logged";
    }

    // Listings page for logged-in users
    @GetMapping("/home-logged-in")
    public String viewListings(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("listings", listDao.findAll());
        model.addAttribute("neighbors", rolesDao.findAll());
        model.addAttribute("users", userDao.findAll());
        return "listings/home-logged-in";
    }
    // Listings page for logged-in users after form submission
    @PostMapping("/home-logged-in")
    public String postIndex(Model model){
        model.addAttribute("user", (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("Listings", listDao.findAll());
        model.addAttribute("neighbors", rolesDao.findAll());
        model.addAttribute("users", userDao.findAll());
        return "listings/home-logged-in";
    }
    // View individual listing by ID
    @GetMapping(path = "/listings/{id}/home-logged-in")
    public String viewListings(@PathVariable long id, Model model){
        model.addAttribute("title", "Individual Post");
        model.addAttribute("listing", listDao.findById(id));
        Listings listing = (Listings) listDao.getReferenceById(id);
        User user = userDao.getReferenceById(listing.getUser().getId());
        model.addAttribute("user", user);
        return "listings/home-logged-in";
    }

}