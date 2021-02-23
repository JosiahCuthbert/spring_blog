package com.codeup.spring_blog.controllers;

import com.codeup.spring_blog.models.Post;
import com.codeup.spring_blog.models.User;
import com.codeup.spring_blog.repositories.PostRepository;
import com.codeup.spring_blog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postDao;

    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao){
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping("/posts")
    public String allPosts(Model model){
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.getOne(id));
        model.addAttribute("user", userDao.getOne(postDao.getOne(id).getUser().getId()));
        return "posts/show";
    }

    @GetMapping("/posts/{id}/edit")
    public String editPostForm(@PathVariable long id, Model model){
        model.addAttribute("post", postDao.getOne(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPost(@PathVariable long id, @ModelAttribute Post post){
//        Post post = new Post(
//                title,
//                body,
//                id
//        );
        postDao.save(post);
        return "redirect:/posts";
    }

//    @PostMapping("/posts/{id}/edit")
//    public String editPost(@PathVariable long id, @RequestParam String title, @RequestParam String body){
//        Post post = new Post(
//                title,
//                body,
//                id
//        );
//        postDao.save(post);
//        return "redirect:/posts";
//    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id){
        postDao.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/create")
    public String createPostForm(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post){
        User user = userDao.getOne(1L);
        post.setUser(user);
        post.setTitle(post.getTitle());
        post.setBody(post.getBody());
        postDao.save(post);
        return "redirect:/posts";
    }

}
