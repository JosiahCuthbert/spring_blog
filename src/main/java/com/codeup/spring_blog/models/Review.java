package com.codeup.spring_blog.models;

import com.codeup.spring_blog.models.User;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
@Table
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(11) UNSIGNED")
    private long id;

    @Column(nullable = false, length = 100)
    private String title;



    @Column(nullable = false)
    private String body;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private long rating;

    @Value("${file-upload-path}")
    private String uploadPath;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    private User user;

    public Review(String title, String body, String image, long rating, long id) {
        this.title = title;
        this.body = body;
        this.image = image;
        this.rating = rating;
        this.id = id;
    }

    public Review() {

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}

