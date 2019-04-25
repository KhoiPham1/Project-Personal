package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    private String passWord;

    //jsonignore để loại bỏ những vòng lặp
    @JsonIgnoreProperties(value = "user",allowSetters = true)
    // fetchtype.eager 1 kiểu trong làm việc với entity
    @OneToMany(targetEntity = Image.class, cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private List<Image> images;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }
}
