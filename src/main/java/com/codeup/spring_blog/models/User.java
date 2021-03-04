package com.codeup.spring_blog.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(11) UNSIGNED")
    private long id;

    @Column(nullable = false, length = 100)
    private String username;

    @Column(nullable = false)
    private String password;


    @Column(nullable = false)
    private String email;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int admin;

    @OneToMany(mappedBy = "user")
        private List<Post> posts;

    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        email = copy.email;
        admin = copy.admin;
        username = copy.username;
        password = copy.password;
    }

    public User(String username, String password, int admin, String email, long id){
        this.username = username;
        this.password = password;
        this.admin = admin;
        this.email = email;
        this.id = id;
    }

    public User() {

    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public long getId() {
            return id;
        }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
