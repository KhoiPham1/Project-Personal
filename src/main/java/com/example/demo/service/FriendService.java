package com.example.demo.service;

import com.example.demo.model.Friend;

public interface FriendService {
    void create (Friend friend);

    Friend findById (Long id);
}
