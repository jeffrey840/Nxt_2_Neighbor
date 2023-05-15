// Import necessary classes and packages
package com.codeup.testrepo.controller;

import com.codeup.testrepo.models.Listings;
import com.codeup.testrepo.models.User;
import com.codeup.testrepo.repositories.ListingRepository;
import com.codeup.testrepo.repositories.RolesRepository;
import com.codeup.testrepo.repositories.UserRepository;
import com.codeup.testrepo.services.EmailService;
import com.codeup.testrepo.services.ProductService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// Define BuyerController class
@Controller
public class BuyerController {
    // Declare and initialize necessary repositories and services
    private final UserRepository userDao;
    private final RolesRepository rolesDao;
    private final ListingRepository listDao;
    private final EmailService emailService;

    private ProductService.ListingService listingService;
    private ProductService service;

    // BuyerController constructor
    public BuyerController(UserRepository userDao, ListingRepository listDao, EmailService emailService, RolesRepository rolesDao) {
        this.userDao = userDao;
        this.listDao = listDao;
        this.rolesDao = rolesDao;
        this.emailService = emailService;
    }

    // Display all listings for the buyer
    @GetMapping(path = "/buyer-redirect")
    public String buyerViewListings(Model model) {
        List<Listings> allDataListings = listDao.findAll();
        model.addAttribute("listings", allDataListings);
        return "listings/viewListingsBuyer";
    }

    // Save a new listing
    @PostMapping("/save-listings")
    public String listingSave(@RequestParam(name = "address") String address,
                              @RequestParam(name = "propType") String prop,
                              @RequestParam(name = "price") double price,
                              @RequestParam(name = "title") String title, Model model) {

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Listings listings = new Listings();
        model.addAttribute("listings", listings);
        listings.setAddress(address);
        listings.setDescription(prop);
        listings.setPrice(price);
        listings.setTitle(title);
        listings.setUser(currentUser);
        listDao.save(listings);
        System.out.println(address);
        System.out.println(prop);
        System.out.println(listings);
        System.out.println(currentUser);
        return "redirect:/listings";
    }

    // View all saved listings
    @GetMapping("/save-listings")
    public String viewAllSavedListings(Model model){
        List<Listings> listings = listDao.findAll();
        model.addAttribute("listings", listings);
        return "listings/buyer-profile";
    }

    // Delete a listing
    @PostMapping("/listing/buyer-profile/delete")
    public String listingsDelete(@RequestParam(name = "delete") long id) {
        listDao.deleteById(id);
        System.out.println(id);
        return "redirect:/listings";
    }
}
