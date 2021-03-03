package com.codeup.spring_blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FeaturesController {

    @GetMapping("/features/tunes")
    public String jazz(){
        return "features/tunes";
    }

}
