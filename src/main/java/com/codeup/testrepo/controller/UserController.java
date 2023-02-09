package com.codeup.testrepo.controller;

import com.codeup.testrepo.models.Roles;
import com.codeup.testrepo.models.User;
import com.codeup.testrepo.repositories.RolesRepository;
import com.codeup.testrepo.repositories.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.management.relation.Role;

@Controller
public class UserController {
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

//    @PostMapping("/sign-up")
//    public String saveUser(@ModelAttribute User user){
//        String hash = passwordEncoder.encode(user.getPassword());
//        user.setPassword(hash);
//        userDao.save(user);
//        return "redirect:/login";
//    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
//        model.addAttribute("role", new Roles());
        return "users/sign-up";}


    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public String handle(@ModelAttribute User user, BindingResult result, RedirectAttributes redirectAttrs,@ModelAttribute Roles roles) {
        if (result.hasErrors()) {
            return "users/sign-up";
        }
//         Save account ...
        redirectAttrs.addAttribute("id", user.getId()).addFlashAttribute("message", "Account created!");
        String hash = passwordEncoder.encode(user.getPassword());
        roles.setUser_role("buyer");
        user.setPassword(hash);
        userDao.save(user);


        return "redirect:/login";
    }

//🟩🟩🟩🟩🟩🟩🟩🟩 Extra feature recover password
//
//    @GetMapping("/forgotMyPassword")
//    public String showForgotPasswordForm(){
//        return "users/forgotPassword";
//    }
//    @RequestMapping(value = "/forgotMyPassword", method = RequestMethod.POST)
//    public String verify() {
////        is user gets these two questions correct redirect to update password
////        if() {
////            return "redirect:/updatePassword";
////        } else {
////            "redirect:/forgotPassword"
////        }
//       return  "redirect:/updatePassword";
//    }
//    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
//    public String verifyCredentials(@ModelAttribute User user) {
//        String username = user.getUsername();
//
//
//        return "redirect:/login";
//    }

//🟩🟩🟩🟩🟩🟩🟩🟩

//    @PostMapping("/sign-up")
//    public String handle(
//            @ModelAttribute User user) {
//        String hash = passwordEncoder.encode(user.getPassword());
//        user.setPassword(hash);
////        user.setRole(roles);
//        userDao.save(user);
//        return "redirect:/login";
//    }

//    @PostMapping("/register")zq
//    public String registerUser(HttpServletRequest request) {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String email = request.getParameter("email");
//        String roleId = request.getParameter("role");
//        // Use the values to create a User object and save it to the database
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        Roles role = RolesRepository.findByName();
//        user.setRole(role);
//        userDao.save(user);
//        return "redirect:/login";
//    }



}

