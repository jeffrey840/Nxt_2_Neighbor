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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
            return "listings/seller-profile";
        } else if (Objects.equals(roles, "neighbor")) {
            return "listings/neighbor-profile";
        }
        return "listings/home-not-logged";
//                System.out.println(roles);
//        System.out.println(id);
    }


    @PostMapping("/home-logged-in")
    public String postIndex(Model model){
        model.addAttribute("Listings", listDao.findAll());
        return "listings/home-logged-in";
    }

    @GetMapping("/listings/home-logged-in")
    public String viewListings(Model model){
        model.addAttribute("listings", listingService.getListing());
        return "listings/home-logged-in";
    }
    //MAPPING FOR VIEWING LISTINGS BY ID
    @GetMapping(path = "/listings/{id}")
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


    //    🟥 uncommenting this returns an error
    //MAPPING FOR EDIT POSTS ON SELLER PAGE
//    @GetMapping(path = "/listings/{id}/seller-profile")
//    public String getEditSeller(@PathVariable long id, Model model){
//        model.addAttribute("title", "Edit Post");
//        Listings listing = (Listings) listDao.getReferenceById(id);
//        model.addAttribute("list", listing);
//        return "listings/seller-profile";
//    }



    //TO EDIT THE POSTS, GRABBING PARAMETERS, SAVING NEW LISTING ON SELLER PAGE
    @PostMapping(path = "/listings/{id}/seller-profile")
    public String sellerEdit(@PathVariable long id, @RequestParam String title, @RequestParam String body){
        Listings listing = (Listings) listDao.getReferenceById(id);
        listing.setTitle(title);
        listing.setDescription(body);
        listDao.save(listing);
        return "redirect:/listings/seller-profile";
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

//    TO EDIT THE POSTS, GRABBING PARAMETERS, SAVING NEW LISTING ON NEIGHBOR PAGE
    @PostMapping(path = "/listings/{id}/neighbor-profile")
    public String neighborEdit(@PathVariable long id, @RequestParam String title, @RequestParam String body){
        Listings listing = (Listings) listDao.getReferenceById(id);
        listing.setTitle(title);
        listing.setDescription(body);
        listDao.save(listing);
        return "redirect:/listings/neighbor-profile";
    }
    //    🟥 uncommenting this returns an error
    //MAPPING ON SELLER PAGE TO CREATE NEW LISTING
//    @GetMapping(path = "listings/seller-profile")
//    public String sellerCreate(Model model){
//        model.addAttribute("list", new Listings());
//        return "listings/seller-profile";
//    }


    //MAPPING ON NEIGHBOR PAGE TO CREATE A BIO SECTION ON PROFILE
    @GetMapping(path = "listings/neighbor-profile")
    public String neighborCreate(Model model){
        model.addAttribute("list", new Listings());
        return "listings/neighbor-profile";
    }

    //    EMAIL SERVICE NOTIFYING OF A NEW LISTING CREATED "POST MAPPING" ON SELLER PAGE

    @PostMapping(path = "listings/seller-profile")
    public String sellerCreate(@ModelAttribute Listings createdListing){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        createdListing.setUser(user);
        emailService.prepareAndSend(createdListing, "Your newest listing: " + createdListing.getTitle(),  ", " + createdListing.getDescription());
        listDao.save(createdListing);
        return "redirect:/listings/seller-profile";
    }


    @GetMapping(path = "/listings/{id}/seller-profile")
    public String sellerDelete(@PathVariable long id) {
        listDao.deleteById(id);
        return "redirect:/listings/seller-profile";
    }


    // DELETE MAPPING FOR BUYER
    @GetMapping(path = "/listings/{id}/buyer-profile")
    public String buyerDelete(@PathVariable long id) {
        listDao.deleteById(id);
        return "redirect:/listings/buyer-profile";
    }

    //MAPPING ON SELLER PAGE TO CREATE NEW LISTING
    @GetMapping(path = "/seller-profile")
    public String sellerCreate(Model model){
        model.addAttribute("list", new Listings());
        return "listings/seller-profile";
    }
    @PostMapping(path = "seller-profile")
    public RedirectView addListing (@ModelAttribute("listing") Listings listings, RedirectAttributes redirectAttributes){
        final RedirectView redirectView = new RedirectView("/listing/addListing", true);
        Listings savedListing = listingService.addListing(listings);
        redirectAttributes.addFlashAttribute("savedListing", savedListing);
        redirectAttributes.addFlashAttribute("addListingSuccess", true);
        return redirectView;
    }

    //DELETE MAPPING FOR NEIGHBOR
    @GetMapping(path = "/listings/{id}/delete")
    public String neighborDelete(@PathVariable long id) {
        listDao.deleteById(id);
        return "redirect:/listings/neighbor-profile";
    }

    //DELETE MAPPING FOR ADMIN
//    @GetMapping(path = "/listings/{id}")
//    public String adminDelete(@PathVariable long id) {
//        listDao.deleteById(id);
//
//        return "redirect:/listings/seller-profile";
//    }


//    @GetMapping(path = "/listings/create")
//    public String getCreate(Model model){
//        model.addAttribute("Listings", new Listings());
//        return "redirect:/listings/neighbor-profile";
//    }
//
//
//    @PostMapping(path = "/listings/create")
//    public String postCreate(@ModelAttribute Listings createdPost){
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        createdPost.setUser(user);
//        emailService.prepareAndSend(createdPost, "Your latest blog post: " + createdPost.getTitle(), "This is the body of your post!" + createdPost.getDescription());
//        listDao.save(createdPost);
//        return "redirect:/listings";
//    }


}
