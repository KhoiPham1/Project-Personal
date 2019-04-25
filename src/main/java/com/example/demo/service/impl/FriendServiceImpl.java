package com.example.demo.service.impl;

import com.example.demo.model.Friend;
import com.example.demo.repository.FriendRepository;
import com.example.demo.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;

public class FriendServiceImpl implements FriendService {
    @Autowired
    private FriendRepository friendRepository;

    @Override
    public void create(Friend friend) {
        friendRepository.save(friend);
    }

    @Override
    public Friend findById(Long id) {
        return friendRepository.findById(id).get();
    }
}
