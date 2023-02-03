package com.codeup.testrepo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class Homecontroller {

    @GetMapping("/")
    @ResponseBody
    public String hello() {

}
