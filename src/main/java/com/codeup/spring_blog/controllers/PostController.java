package com.codeup.spring_blog.controllers;

import com.codeup.spring_blog.models.Post;
import com.codeup.spring_blog.models.User;
import com.codeup.spring_blog.repositories.PostRepository;
import com.codeup.spring_blog.repositories.UserRepository;
import com.codeup.spring_blog.services.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@Controller
public class PostController {

    private final PostRepository postDao;

    private final UserRepository userDao;

    private final EmailService emailService;

    @Value("${file-upload-path}")
    private String uploadPath;

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
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(userDao.getOne((loggedInUser.getId())));

        postDao.save(post);

        String subject = "Post Edited";
        String body = "The post titled " + post.getTitle() + "was edited.";
        emailService.prepareAndSendPost(post, subject, body);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id){
        Post post = postDao.getOne(id);
        postDao.deleteById(id);

        String subject = "Post Deleted";
        String body = "The post titled " + post.getTitle() + "was deleted.";
        emailService.prepareAndSendPost(post, subject, body);
        return "redirect:/posts";
    }

    @GetMapping("/posts/create")
    public String createPostForm(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post, @RequestParam(name = "img") MultipartFile uploadedFile, Model model){
        String filename = uploadedFile.getOriginalFilename();
        String filepath = Paths.get(uploadPath, filename).toString();
        File destinationFile = new File(filepath);
        try {
            uploadedFile.transferTo(destinationFile);
            model.addAttribute("message", "File successfully uploaded!");
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Oops! Something went wrong! " + e);
        }

        User user = userDao.getOne(1L);
        post.setUser(user);
        post.setTitle(post.getTitle());
        post.setBody(post.getBody());
        post.setImage("/uploads/" + filename);
        postDao.save(post);

        String subject = "New Post Created";
        String body = "A new post was created by user " + user.getUsername() + ". The post title is " + post.getTitle() + ".";
        emailService.prepareAndSendPost(post, subject, body);
        return "redirect:/posts";
    }

}
