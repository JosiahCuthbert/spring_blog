package com.codeup.spring_blog.repositories;
import com.codeup.spring_blog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("from Post a where a.type = ?1")
    List<Post> getAllByType(String type);

}
