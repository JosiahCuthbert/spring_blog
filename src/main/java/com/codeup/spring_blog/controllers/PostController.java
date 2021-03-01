package com.codeup.spring_blog.controllers;

import com.codeup.spring_blog.models.Post;
import com.codeup.spring_blog.models.User;
import com.codeup.spring_blog.repositories.PostRepository;
import com.codeup.spring_blog.repositories.UserRepository;
import com.codeup.spring_blog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postDao;

    private final UserRepository userDao;

    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService){
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/posts";
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
    public String editPost(@ModelAttribute Post post){
        postDao.save(post);

        String subject = "Post Edited";
        String body = "The post titled " + post.getTitle() + "was edited.";
        emailService.prepareAndSend(post, subject, body);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id){
        Post post = postDao.getOne(id);
        postDao.deleteById(id);

        String subject = "Post Deleted";
        String body = "The post titled " + post.getTitle() + "was deleted.";
        emailService.prepareAndSend(post, subject, body);
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

        String subject = "New Post Created";
        String body = "A new post was created by user " + user.getUsername() + ". The post title is " + post.getTitle() + ".";
        emailService.prepareAndSend(post, subject, body);
        return "redirect:/posts";
    }

}
