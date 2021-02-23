package com.codeup.spring_blog.repositories;

import com.codeup.spring_blog.models.Post;
import com.codeup.spring_blog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {



}
