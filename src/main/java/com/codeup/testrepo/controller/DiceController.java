package com.codeup.testrepo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DiceController {
    @GetMapping("/roll-dice")
    public String roll() {
        return "roll-dice";
    }

    @PostMapping("/roll-dice/results")
    public String roll(@RequestParam(name = "n")@PathVariable String n, Model model) {
//        storing a random #
        int random = (int) Math.floor(Math.random() * 6) + 1;
//        default guess is false for all values
        boolean guess = false;

//        pass in the user input and the random # in the html
        model.addAttribute("n", n);
        model.addAttribute("random", random);
//        if else statement to compare n and random
        if(n.equals(Integer.toString(random))) {
//            if true change the initial guess value
            guess = true;
        }
//        pass in user guess
        model.addAttribute("guess", guess);
        return "roll-dice";
    }
}