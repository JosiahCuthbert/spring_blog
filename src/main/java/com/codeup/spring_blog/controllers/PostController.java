package com.codeup.spring_blog.controllers;

import com.codeup.spring_blog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @GetMapping("/posts")
    public String allPosts(Model model){

        Post post1 = new Post("post 1", "this is the first post", 1);
        Post post2 = new Post("post 2", "this is the second post", 2);

        List<Post> allPosts = new ArrayList<>();
        allPosts.add(post1);
        allPosts.add(post2);

        model.addAttribute("posts", allPosts);

        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String singlePost(Model model) {
//        return "post #" + id;
        Post post = new Post("post 1", "this is the post", 1);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
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
