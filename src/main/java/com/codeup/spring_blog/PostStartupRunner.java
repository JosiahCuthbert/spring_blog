package com.codeup.spring_blog;

import com.codeup.spring_blog.models.Post;
import com.codeup.spring_blog.models.User;
import com.codeup.spring_blog.repositories.PostRepository;
import com.codeup.spring_blog.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class PostStartupRunner implements CommandLineRunner {

    private final UserRepository userDao;
    private final PostRepository postDao;

    public PostStartupRunner(UserRepository userDao, PostRepository postDao) {
        this.userDao = userDao;
        this.postDao = postDao;
    }

    @Override
    public void run(String... args) throws Exception {
        // don't run if there's already any users in the database
        if (userDao.count() != 0) {
            return;
        }
        User user = new User();
        user.setUsername("joe");
        user.setEmail("josiah.thomas.cuthbert@gmail.com");
        user.setPassword("joe1996");
        userDao.save(user);

        Post post = new Post();
        post.setTitle("Demo title");
        post.setBody("Demo body");
        post.setUser(user);
        postDao.save(post);
    }
}
