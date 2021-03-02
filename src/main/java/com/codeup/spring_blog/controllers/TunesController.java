package com.codeup.spring_blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TunesController {

    @GetMapping("/tunes/jazz")
    public String jazz(){
        return "tunes/jazz";
    }

}
