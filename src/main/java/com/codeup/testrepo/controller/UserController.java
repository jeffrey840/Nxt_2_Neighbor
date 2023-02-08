package com.codeup.testrepo.controller;

import com.codeup.testrepo.models.Roles;
import com.codeup.testrepo.models.User;
//import com.codeup.testrepo.repositories.RolesRepository;
//import com.codeup.testrepo.repositories.RolesRepository;
import com.codeup.testrepo.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    private UserRepository userDao;

//    private RolesRepository rolesDao;

    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
//        this.rolesDao = rolesDao;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
//        model.addAttribute("role", new Roles());
        return "users/sign-up";
    }
//
//    @PostMapping("/sign-up")
//    public String saveUser(@ModelAttribute User user){
//        String hash = passwordEncoder.encode(user.getPassword());
//        user.setPassword(hash);
//        userDao.save(user);
//        return "redirect:/login";
//    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public String handle(@ModelAttribute User user, BindingResult result, RedirectAttributes redirectAttrs) {
        System.out.println("end point hit");
        System.out.println(user.getRoles().getUser_role());
//        if (result.hasErrors()) {
//            return "users/sign-up";
//        }
        // Save account ...
        redirectAttrs.addAttribute("id", user.getId()).addFlashAttribute("message", "Account created!");
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/login";
    }
    


}

