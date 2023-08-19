package com.wisdom.bevm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    @GetMapping("/admin")
    public String adminHome(){
        return "admin-page";
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/layout")
    public String layout(){
        return "_layout";
    }

    @GetMapping("/login")
    public String viewLoginPage(){
        return "login";
    }

    @GetMapping("/logout-success")
    public String logout(){
        return "login";
    }
}
