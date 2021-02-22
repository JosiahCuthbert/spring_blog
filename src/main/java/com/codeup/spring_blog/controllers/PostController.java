package com.codeup.spring_blog.controllers;

import com.codeup.spring_blog.models.Post;
import com.codeup.spring_blog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao){
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String allPosts(Model model){
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.getOne(id));
        return "posts/show";
    }

    @GetMapping("/posts/{id}/edit")
    public String editPostForm(@PathVariable long id, Model model){
        model.addAttribute("post", postDao.getOne(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPost(@PathVariable long id, @RequestParam String title, @RequestParam String body){
        Post post = new Post(
                title,
                body,
                id
        );
        postDao.save(post);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id){
        postDao.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String createPostForm(Model model){
        return "posts/create";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(){
        return "creating new post..";
    }

}
