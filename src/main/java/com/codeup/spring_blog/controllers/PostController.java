package com.codeup.spring_blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @RequestMapping("/posts")
    @ResponseBody
    public String posts(){
        return "all posts";
    }

    @RequestMapping("/posts/{id}")
    @ResponseBody
    public String posts(@PathVariable long id){
        return "post #" + id;
    }

    @RequestMapping("/posts/create")
    @ResponseBody
    public String createPostForm(){
        return "create post form";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(){
        return "creating new post..";
    }

}
