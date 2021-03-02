package com.codeup.spring_blog.repositories;
import com.codeup.spring_blog.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {



}