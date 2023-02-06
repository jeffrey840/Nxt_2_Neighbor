package com.codeup.testrepo.controller;

import com.codeup.testrepo.models.Listings;
import com.codeup.testrepo.repositories.UserRepository;
import com.codeup.testrepo.services.EmailService;
import com.codeup.testrepo.models.User;
import com.codeup.testrepo.repositories.PostRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ListingController {

    private final UserRepository userDao;
    private final PostRepository postDao;

    private final EmailService emailService;

    public ListingController(PostRepository postDao,UserRepository userDao, EmailService emailService){
        this.userDao = userDao;
        this.postDao = postDao;
        this.emailService = emailService;

    }


    @GetMapping("/posts")
    public String postIndex(Model model){
        model.addAttribute("posts", postDao.findAll());
        model.addAttribute("title", "Post Index");
        return "posts/index";
    }

    @GetMapping(path = "/posts/{id}")
    public String viewPost(@PathVariable long id, Model model){
        model.addAttribute("title", "Individual Post");
        model.addAttribute("post", postDao.findById(id));
        Listings listing = postDao.getReferenceById(id);
        User user = userDao.getReferenceById(listing.getUser().getId());
        model.addAttribute("postTitle", listing.getTitle());
        model.addAttribute("postBody", listing.getBody());
        model.addAttribute("postID", listing.getId());
        model.addAttribute("userEmail", user.getEmail());
        model.addAttribute("user", user);
        return "posts/show";
    }

    @GetMapping(path = "/posts/{id}/edit")
    public String getEdit(@PathVariable long id, Model model){
        model.addAttribute("title", "Edit Post");
        Listings listing = listingDao.getReferenceById(id);
        model.addAttribute("listing", listing);
        return "posts/edit";
    }

    @PostMapping(path = "/posts/{id}/edit")
    public String postEdit(@PathVariable long id, @RequestParam String title, @RequestParam String body){
        Listings listing = listingDao.getReferenceById(id);
        listing.setTitle(title);
        listing.setBody(body);
        listingDao.save(listing);
        return "redirect:/posts";
    }


    @GetMapping(path = "/posts/create")
    public String getCreate(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }


    @PostMapping(path = "/posts/create")
    public String postCreate(@ModelAttribute Listings createdListing){
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    createdListing.setUser(user);
        emailService.prepareAndSend(createdListing, "Your latest blog post: " + createdListing.getTitle(), "This is the body of your post!" + createdPost.getBody());
        lsitingDao.save(createdListing);
        return "redirect:/posts";
    }

    @GetMapping(path="/posts/{id}/delete")
    public String postDelete(@PathVariable long id){
        postDao.deleteById(id);
        return "redirect:/posts";
    }
}
