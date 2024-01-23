package com.group1.studentsocialbackend.service;

import com.group1.studentsocialbackend.mapper.UserMapper;
import com.group1.studentsocialbackend.PO.User;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public User Register(User user) {
        userMapper.insert(user);
        return user;
    }
}
