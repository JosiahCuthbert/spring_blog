package com.codeup.spring_blog.controllers;

import com.codeup.spring_blog.models.Post;
import com.codeup.spring_blog.models.User;
import com.codeup.spring_blog.repositories.PostRepository;
import com.codeup.spring_blog.repositories.UserRepository;
import com.codeup.spring_blog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenticationController {

    private final UserRepository userDao;

    public AuthenticationController(UserRepository userDao){
        this.userDao = userDao;
    }

    @GetMapping("/users/login")
    public String showLoginForm() {
        return "users/login";
    }

    @GetMapping("/users/admin")
    public String userAdmin(Model model){
        model.addAttribute("users", userDao.findAll());
        return "users/admin";
    }

    @GetMapping("/users/{id}/edit")
    public String editForm(@PathVariable long id, Model model) {
        model.addAttribute("user", userDao.getOne(id));
        return "users/edit";
    }

    @PostMapping("/users/{id}/edit")
    public String editUser(@ModelAttribute User user){
        userDao.save(user);
        return "redirect:/users/admin";
    }

    @PostMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable long id){
        userDao.deleteById(id);
        return "redirect:/users/admin";
    }

}
