package com.codeup.testrepo.controller;

import com.codeup.testrepo.models.Listings;
import com.codeup.testrepo.models.User;
import com.codeup.testrepo.repositories.ListingRepository;
import com.codeup.testrepo.repositories.UserRepository;
import com.codeup.testrepo.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // Indicate that this class serves as a controller in the MVC pattern.
public class SellerCreateController {
    // Define the repositories and services used in this controller.
    private final ListingRepository listDao;
    private final UserRepository userDao;
    private final EmailService emailService;

    // Inject dependencies via the constructor.
    public SellerCreateController(ListingRepository listDao, EmailService emailService, UserRepository userDao) {
        this.listDao = listDao;
        this.emailService = emailService;
        this.userDao = userDao;
    }

    // Endpoint to display all listings of the current user (seller).
    @GetMapping(path = "/seller-profile")
    public String getPost(Model model) {
        // Get the current authenticated user.
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // Get all listings of the current user.
        List<Listings> listings = listDao.findAllByUser(user);
        // Add listings to the model to be used in the view.
        model.addAttribute("listings", listings);
        // Redirect to the /listings page.
        return "redirect:/listings";
    }

    // Endpoint to display an individual listing.
    @GetMapping("/seller-profile/{id}")
    public String getIndividualListing(@PathVariable long id, Model model) {
        // Get the listing by id.
        Listings currentPost = listDao.findById(id);
        // Add the listing to the model.
        model.addAttribute("listings", currentPost);
        // Direct to the seller-profile view with the current listing.
        return "listings/seller-profile";
    }

    // Endpoint to show the form for creating a new listing.
    @GetMapping(path = "/seller-create")
    public String showCreateForm(Model model) {
        // Prepare a new listing object to bind form data.
        model.addAttribute("listings", new Listings());
        // Direct to the seller-create view which contains the form.
        return "listings/seller-create";
    }

    // Endpoint to handle form submission for creating a new listing.
    @PostMapping(path = "/listings/seller-create")
    public String createListing(@ModelAttribute Listings listings) {
        // Get the current authenticated user.
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // Set the current user as the owner of the listing.
        listings.setUser(user);
        // Send an email notification about the creation of a new listing.
        emailService.prepareAndSend(listings, "New Post Created", "A new post has been created");
        // Save the new listing in the database.
        listDao.save(listings);
        // Redirect to the seller-profile page.
        return "redirect:/seller-profile";
    }

    // Endpoint to delete a listing.
    @PostMapping("/listing/seller-profile/delete")
    public String listingsDelete(@RequestParam(name = "delete") long id) {
        // Delete the listing by id.
        listDao.deleteById(id);
        // Redirect to the seller-profile page.
        return "redirect:/seller-profile";
    }

    // Endpoint to show the form for updating a listing.
    @GetMapping("/listings/{id}/seller-update")
    public String showUpdateForm(@PathVariable long id, Model model) {
        // Get the listing to be updated by id.
        Listings currentListings = listDao.getReferenceById(id);
        // Add the listing to the model.
        model.addAttribute("listings", currentListings);
        // Direct to the seller-update view which contains the form.
        return "listings/seller-update";
    }
    @PostMapping("/listings/seller-update")
    public String editListing(@RequestParam(name = "listingId")String id, @ModelAttribute Listings listings) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        listings.setUser(user);
        listings.setId(Long.parseLong(id));
        System.out.println(id);
        listDao.save(listings);
        return "redirect:/seller-profile";
    }
}
