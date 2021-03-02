package com.codeup.spring_blog.controllers;

import com.codeup.spring_blog.models.Review;
import com.codeup.spring_blog.models.User;
import com.codeup.spring_blog.repositories.ReviewRepository;
import com.codeup.spring_blog.repositories.ReviewRepository;
import com.codeup.spring_blog.repositories.UserRepository;
import com.codeup.spring_blog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReviewController {

    private final ReviewRepository reviewDao;

    private final UserRepository userDao;

    private final EmailService emailService;

    public ReviewController(ReviewRepository reviewDao, UserRepository userDao, EmailService emailService){
        this.reviewDao = reviewDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @GetMapping("/reviews")
    public String allReviews(Model model){
        model.addAttribute("reviews", reviewDao.findAll());
        return "reviews/index";
    }

    @GetMapping("/reviews/{id}")
    public String singleReview(@PathVariable long id, Model model) {
        model.addAttribute("review", reviewDao.getOne(id));
        model.addAttribute("user", userDao.getOne(reviewDao.getOne(id).getUser().getId()));
        return "reviews/show";
    }

    @GetMapping("/reviews/{id}/edit")
    public String editReviewForm(@PathVariable long id, Model model){
        model.addAttribute("review", reviewDao.getOne(id));
        return "reviews/edit";
    }

    @PostMapping("/reviews/{id}/edit")
    public String editReview(@ModelAttribute Review review){
        reviewDao.save(review);

        String subject = "Review Edited";
        String body = "The review titled " + review.getTitle() + "was edited.";
        emailService.prepareAndSend(review, subject, body);
        return "redirect:/reviews";
    }

    @PostMapping("/reviews/{id}/delete")
    public String deleteReview(@PathVariable long id){
        Review review = reviewDao.getOne(id);
        reviewDao.deleteById(id);

        String subject = "Review Deleted";
        String body = "The review titled " + review.getTitle() + "was deleted.";
        emailService.prepareAndSend(review, subject, body);
        return "redirect:/reviews";
    }

    @GetMapping("/reviews/create")
    public String createReviewForm(Model model){
        model.addAttribute("review", new Review());
        return "reviews/create";
    }

    @PostMapping("/reviews/create")
    public String createReview(@ModelAttribute Review review){
        User user = userDao.getOne(1L);
        review.setUser(user);
        review.setTitle(review.getTitle());
        review.setBody(review.getBody());
        reviewDao.save(review);

        String subject = "New Review Created";
        String body = "A new review was created by user " + user.getUsername() + ". The review title is " + review.getTitle() + ".";
        emailService.prepareAndSend(review, subject, body);
        return "redirect:/reviews";
    }

}
