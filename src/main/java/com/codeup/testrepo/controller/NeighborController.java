package com.codeup.testrepo.controller;

import com.codeup.testrepo.models.Listings;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NeighborController {
    private final UserRepository userDao;
    private final RolesRepository rolesDao;
    private final ListingRepository listDao;
    private final EmailService emailService;

    private ProductService.ListingService listingService;
    private ProductService service;


    public NeighborController(UserRepository userDao, ListingRepository listDao, EmailService emailService, RolesRepository rolesDao) {
        this.userDao = userDao;
        this.listDao = listDao;
        this.rolesDao = rolesDao;
        this.emailService = emailService;
    }
    @GetMapping(path = "/listings/{id}/delete")
    public String neighborDelete(@PathVariable long id) {
        listDao.deleteById(id);
        return "redirect:/listings/neighbor-profile";
    }
    @GetMapping(path = "listings/neighbor-profile")
    public String neighborCreate(Model model){
        model.addAttribute("list", new Listings());
        return "listings/neighbor-profile";
    }
    @PostMapping(path = "/listings/{id}/neighbor-profile")
    public String neighborEdit(@PathVariable long id, @RequestParam String title, @RequestParam String body){
        Listings listing = (Listings) listDao.getReferenceById(id);
        listing.setTitle(title);
        listing.setDescription(body);
        listDao.save(listing);
        return "redirect:/listings/neighbor-profile";
    }
    //    🟥 uncommenting this returns an error
    //MAPPING FOR EDIT POST ON NEIGHBOR-PAGE
//    @GetMapping(path = "/listings/{id}/neighbor-profile")
//    public String getEditNeighbor(@PathVariable long id, Model model){
//        model.addAttribute("title", "Edit Post");
//        Listings listing = (Listings) listDao.getReferenceById(id);
//        model.addAttribute("list", listing);
//        return "listings/neighbor-profile";
//    }
}
