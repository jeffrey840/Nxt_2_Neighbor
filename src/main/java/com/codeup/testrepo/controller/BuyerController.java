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


@Controller
public class BuyerController {
    private final UserRepository userDao;
    private final RolesRepository rolesDao;
    private final ListingRepository listDao;
    private final EmailService emailService;

    private ProductService.ListingService listingService;
    private ProductService service;


    public BuyerController(UserRepository userDao, ListingRepository listDao, EmailService emailService, RolesRepository rolesDao) {
        this.userDao = userDao;
        this.listDao = listDao;
        this.rolesDao = rolesDao;
        this.emailService = emailService;
    }
//    @GetMapping(path = "/listings/{id}/buyer-profile")
//    public String buyerDelete(@PathVariable long id) {
//        listDao.deleteById(id);
//        return "redirect:/listings/buyer-profile";
//    }
    @GetMapping(path = "/buyer-redirect")
    public String buyerViewListings(){
       return "/listings/viewListingsBuyer";
    }

    @PostMapping ("/save-listings")
    public String listingSave(@RequestParam(name = "address") String address,
                                 @RequestParam(name = "propType") String prop,
                                 @RequestParam (name = "price") double price,
                                 @RequestParam(name = "title") String title){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Listings listings = new Listings();
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
        return "/listings/buyer-profile";
    }

}
