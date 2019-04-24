package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService  {
    void create (User user);

    User findById(Long id);


}
