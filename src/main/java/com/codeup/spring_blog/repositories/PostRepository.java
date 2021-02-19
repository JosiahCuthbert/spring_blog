package com.codeup.spring_blog.repositories;
import com.codeup.spring_blog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {



}
