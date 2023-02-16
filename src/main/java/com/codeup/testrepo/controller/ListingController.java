package com.codeup.testrepo.controller;
import com.codeup.testrepo.models.Listings;
import com.codeup.testrepo.models.Roles;
import com.codeup.testrepo.repositories.RolesRepository;
import com.codeup.testrepo.repositories.UserRepository;
import com.codeup.testrepo.services.EmailService;
import com.codeup.testrepo.models.User;
import com.codeup.testrepo.repositories.ListingRepository;
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

    private ProductService.ListingService listingService;
    private ProductService service;


    public ListingController(UserRepository userDao, ListingRepository listDao, EmailService emailService, RolesRepository rolesDao) {
        this.userDao = userDao;
        this.listDao = listDao;
        this.rolesDao = rolesDao;
        this.emailService = emailService;
    }

//    MAPPING TO VIEW LISTINGS AS A NON REGISTERED USER
//    @GetMapping("/listings")
//    public String homeNotLogged(Model model){
//        model.addAttribute("listings", listDao.findAll());
//        model.addAttribute("title", "Home");
//        return "redirect: /listings/home-logged";
//    }

//    @RequestMapping("/")
//    public String viewHomePage(Model model, @Param("keyword") String keyword) {
//        List<Listings> listProducts = service.listAll(keyword);
//        model.addAttribute("listProducts", listProducts);
//        model.addAttribute("keyword", keyword);
//
//        return "index";
//    }
@GetMapping("/")
public String welcomePage() {
    return "listings/home-not-logged";
}

    @GetMapping ("/listings")
    public String userHome(Model model) {
        model.addAttribute("Users", userDao.findAll());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        User user1 = userDao.findByUsername(currentUser);
        long id = user1.getId();
        User user = userDao.getReferenceById(id);
        Roles roles1 = rolesDao.getReferenceById(user.getRole().getId());
        String roles = roles1.getUser_role();
        if(Objects.equals(roles, "buyer")){
            return "listings/buyer-profile";
        } else if (Objects.equals(roles, "seller")) {
            List<Listings> listings = listDao.findAll();
            model.addAttribute("listings", listings);
            return "listings/seller-profile";
        } else if (Objects.equals(roles, "neighbor")) {
            return "listings/neighbor-profile";
        }
        return "listings/home-not-logged";
    }
    @GetMapping("/home-logged-in")
    public String viewListings(Model model){
        model.addAttribute("listings", listDao.findAll());
        model.addAttribute("neighbors", rolesDao.findAll());
        model.addAttribute("users", userDao.findAll());
        return "listings/home-logged-in";
    }
    @PostMapping("/home-logged-in")
    public String postIndex(Model model){
        model.addAttribute("Listings", listDao.findAll());
        model.addAttribute("neighbors", rolesDao.findAll());
        model.addAttribute("users", userDao.findAll());
        return "listings/home-logged-in";
    }
    //MAPPING FOR VIEWING LISTINGS BY ID
    @GetMapping(path = "/listings/{id}/home-logged-in")
    public String viewListings(@PathVariable long id, Model model){
        model.addAttribute("title", "Individual Post");
        model.addAttribute("listing", listDao.findById(id));
        Listings listing = (Listings) listDao.getReferenceById(id);
        User user = userDao.getReferenceById(listing.getUser().getId());
//        model.addAttribute("postTitle", listing.getTitle());
//        model.addAttribute("postBody", listing.getDescription());
//        model.addAttribute("postID", listing.getId());
//        model.addAttribute("userEmail", user.getEmail());
        model.addAttribute("user", user);
        return "listings/home-logged-in";
    }

}
