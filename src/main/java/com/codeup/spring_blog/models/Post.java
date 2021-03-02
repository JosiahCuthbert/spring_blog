package com.codeup.spring_blog.models;
import com.sun.istack.Nullable;

import javax.persistence.*;

@Entity
@Table
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(11) UNSIGNED")
    private long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String body;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String image;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
        private User user;

        public Post(String title, String body, String type, String image, long id){
            this.title = title;
            this.body = body;
            this.type = type;
            this.image = image;
            this.id = id;
        }

        public Post() {

        }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
