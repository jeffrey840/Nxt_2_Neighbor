package com.codeup.testrepo.controller;
import com.codeup.testrepo.models.Listings;
import com.codeup.testrepo.repositories.UserRepository;
import com.codeup.testrepo.services.EmailService;
import com.codeup.testrepo.models.User;
import com.codeup.testrepo.repositories.ListingRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ListingController {
    private final ListingRepository listDao;
    private final UserRepository userDao;
    private final EmailService emailService;

    public ListingController(ListingRepository listDao, UserRepository userDao, EmailService emailService){
        this.userDao = userDao;
        this.listDao = listDao;
        this.emailService = emailService;
    }
    //MAPPING TO VIEW LISTINGS AS A NON REGISTERED USER
    @GetMapping("/listings")
    public String homeNotLogged(Model model){
        model.addAttribute("listings", listDao);
        model.addAttribute("title", "Home");
        return "/listings/home-not-logged";
    }

    //MAPPING FOR VIEWING LISTINGS BY ID
    @GetMapping(path = "/listings/{id}")
    public String viewListings(@PathVariable long id, Model model){
        model.addAttribute("title", "Individual Listing");
        model.addAttribute("listing", listDao.findById(id));
        Listings listing = (Listings) listDao.getReferenceById(id);
        User user = userDao.getReferenceById(listing.getUser().getId());
        model.addAttribute("listingTitle", listing.getTitle());
        model.addAttribute("listingBody", listing.getDescription());
        model.addAttribute("listingID", listing.getId());
        model.addAttribute("userEmail", user.getEmail());
        model.addAttribute("user", user);
        return "listings/home-logged-in";
    }

    @GetMapping(path="/listings/{id}/delete")
    public String postDelete(@PathVariable long id){
        listDao.deleteById(id);
        return "listings/home-logged-in";
    }

    //MAPPING FOR EDIT POSTS ON SELLER PAGE
    @GetMapping(path = "/listings/{id}/seller-profile")
    public String getEditSeller(@PathVariable long id, Model model){
        model.addAttribute("title", "Edit Post");
        Listings listing = (Listings) listDao.getReferenceById(id);
        model.addAttribute("list", listing);
        return "listings/seller-profile";
    }

    //MAPPING FOR EDIT POST ON NEIGHBOR-PAGE
    @GetMapping(path = "/listings/{id}/neighbor-profile")
    public String getEditNeighbor(@PathVariable long id, Model model){
        model.addAttribute("title", "Edit Post");
        Listings listing = (Listings) listDao.getReferenceById(id);
        model.addAttribute("list", listing);
        return "listings/neighbor-profile";
    }

    //TO EDIT THE POSTS, GRABBING PARAMETERS, SAVING NEW LISTING ON SELLER PAGE
    @PostMapping(path = "/listings/{id}/seller-profile")
    public String sellerEdit(@PathVariable long id, @RequestParam String title, @RequestParam String body){
        Listings listing = (Listings) listDao.getReferenceById(id);
        listing.setTitle(title);
        listing.setDescription(body);
        listDao.save(listing);
        return "redirect:/listings/seller-profile";
    }

    //TO EDIT THE POSTS, GRABBING PARAMETERS, SAVING NEW LISTING ON NEIGHBOR PAGE
    @PostMapping(path = "/listings/{id}/neighbor-profile")
    public String neighborEdit(@PathVariable long id, @RequestParam String title, @RequestParam String body){
        Listings listing = (Listings) listDao.getReferenceById(id);
        listing.setTitle(title);
        listing.setDescription(body);
        listDao.save(listing);
        return "redirect:/listings/neighbor-profile";
    }

    //MAPPING ON SELLER PAGE TO CREATE NEW LISTING
    @GetMapping(path = "listings/seller-profile")
    public String sellerCreate(Model model){
        model.addAttribute("list", new Listings());
        return "listings/seller-profile";
    }

    //MAPPING ON NEIGHBOR PAGE TO CREATE A BIO SECTION ON PROFILE
    @GetMapping(path = "listings/neighbor-profile")
    public String neighborCreate(Model model){
        model.addAttribute("list", new Listings());
        return "listings/neighbor-profile";
    }

    //EMAIL SERVICE NOTIFYING OF A NEW LISTING CREATED "POST MAPPING" ON SELLER PAGE
    @PostMapping(path = "listings/seller-profile")
    public String sellerCreate(@ModelAttribute Listings createdListing){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        createdListing.setUser(user);
            emailService.prepareAndSend(createdListing, "Your newest listing: " + createdListing.getTitle(),  ", " + createdListing.getDescription());
            listDao.save(createdListing);
            return "redirect:/listings/seller-profile";
    }

    //DELETE MAPPING FOR SELLER
    @GetMapping(path = "/listings/{id}/seller-profile")
    public String sellerDelete(@PathVariable long id) {
        listDao.deleteById(id);
        return "redirect:/listings/seller-profile";
    }

    // DELETE MAPPING FOR BUYER
    @GetMapping(path = "/listings/{id}/buyer-profile")
    public String buyerDelete(@PathVariable long id) {
        listDao.deleteById(id);
        return "redirect:/listings/seller-profile";
    }

    //DELETE MAPPING FOR NEIGHBOR
    @GetMapping(path = "/listings/{id}/neighbor-profile")
    public String neighborDelete(@PathVariable long id) {
        listDao.deleteById(id);
        return "redirect:/listings/seller-profile";
    }

    //DELETE MAPPING FOR ADMIN
    @GetMapping(path = "/listings/{id}")
    public String adminDelete(@PathVariable long id) {
        listDao.deleteById(id);
        return "redirect:/listings/seller-profile";
    }
}
