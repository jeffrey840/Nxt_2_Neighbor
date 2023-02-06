package com.codeup.testrepo.controller;

import com.codeup.testrepo.models.Post;
import com.codeup.testrepo.models.User;
import com.codeup.testrepo.repositories.PostRepository;
import com.codeup.testrepo.repositories.UserRepository;
import com.codeup.testrepo.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final UserRepository userDao;
    private final PostRepository postDao;

    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService){
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
        Post post = postDao.getReferenceById(id);
        User user = userDao.getReferenceById(post.getUser().getId());
        model.addAttribute("postTitle", post.getTitle());
        model.addAttribute("postBody", post.getBody());
        model.addAttribute("postID", post.getId());
        model.addAttribute("userEmail", user.getEmail());
        model.addAttribute("user", user);
        return "posts/show";
    }

    @GetMapping(path = "/posts/{id}/edit")
    public String getEdit(@PathVariable long id, Model model){
        model.addAttribute("title", "Edit Post");
        Post post = postDao.getReferenceById(id);
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping(path = "/posts/{id}/edit")
    public String postEdit(@PathVariable long id, @RequestParam String title, @RequestParam String body){
        Post post = postDao.getReferenceById(id);
        post.setTitle(title);
        post.setBody(body);
        postDao.save(post);
        return "redirect:/posts";
    }


    @GetMapping(path = "/posts/create")
    public String getCreate(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }


    @PostMapping(path = "/posts/create")
    public String postCreate(@ModelAttribute Post createdPost){
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    createdPost.setUser(user);
        emailService.prepareAndSend(createdPost, "Your latest blog post: " + createdPost.getTitle(), "This is the body of your post!" + createdPost.getBody());
        postDao.save(createdPost);
        return "redirect:/posts";
    }

    @GetMapping(path="/posts/{id}/delete")
    public String postDelete(@PathVariable long id){
        postDao.deleteById(id);
        return "redirect:/posts";
    }
}
