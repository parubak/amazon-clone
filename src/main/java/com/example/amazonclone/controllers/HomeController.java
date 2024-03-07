package com.example.amazonclone.controllers;

import com.example.amazonclone.repos.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("/deals")
    public String deals() {

//        return "index";
        return "deals";
    }


}
