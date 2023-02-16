package com.codeup.testrepo.controller;

import com.codeup.testrepo.models.Listings;
import com.codeup.testrepo.models.User;
import com.codeup.testrepo.repositories.ListingRepository;
import com.codeup.testrepo.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SellerCreateController {
    private final ListingRepository listDao;

    private final EmailService emailService;

    public SellerCreateController(ListingRepository listDao, EmailService emailService) {
        this.listDao = listDao;
        this.emailService = emailService;
    }

    //CREATE
    @GetMapping(path = "/seller-profile")
    public String getPost(Model model) {
        List<Listings> listings = listDao.findAll();
        model.addAttribute("listings", listings);
        return "listings/seller-profile";
    }

    @GetMapping("/seller-profile/{id}")
    public String getIndividualListing(@PathVariable long id, Model model) {
        Listings currentPost = listDao.findById(id);
        model.addAttribute("listings", currentPost);
        return "listings/seller-profile";
    }

    @GetMapping(path = "/seller-create")
    public String showCreateForm(Model model) {
        model.addAttribute("listings", new Listings());
        return "/listings/seller-create";
    }

    @PostMapping(path = "/listings/seller-create")
    public String createListing(@ModelAttribute Listings listings) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        listings.setUser(user);
        emailService.prepareAndSend(listings, "New Post Created", "A new post has been created");
        listDao.save(listings);
        return "redirect:/seller-profile";
    }

    //DELETE
    @PostMapping("/listing/seller-profile/delete")
    public String postDelete(@RequestParam(name = "delete") long id) {
        listDao.deleteById(id);
        return "redirect:/seller-profile";
    }

}
