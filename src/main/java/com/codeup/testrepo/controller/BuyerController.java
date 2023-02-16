package com.codeup.testrepo.controller;

import com.codeup.testrepo.repositories.ListingRepository;
import com.codeup.testrepo.repositories.RolesRepository;
import com.codeup.testrepo.repositories.UserRepository;
import com.codeup.testrepo.services.EmailService;
import com.codeup.testrepo.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

}
