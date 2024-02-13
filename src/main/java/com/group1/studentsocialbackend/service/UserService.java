package com.group1.studentsocialbackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.group1.studentsocialbackend.mapper.UserMapper;
import com.group1.studentsocialbackend.PO.User;
import com.group1.studentsocialbackend.util.JwtUtil;
import com.group1.studentsocialbackend.util.encryptUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User createOneUser(User user) {
        user.setPassword(encryptUtil.encodePassword(user.getPassword()));
        userMapper.insert(user);
        return user;
    }
    public User getUserByEmail(String email) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("email", email));
    }

    /**
     * return a token if sign successfully(Token will be saved to Mysql or Redis based on the setting)
     * otherwise, return null
     * @return token
     */
    public String signIn(User user) {
        User queryedUser = getUserByEmail(user.getEmail());

        if (queryedUser == null || !passwordEncoder.matches(user.getPassword(), queryedUser.getPassword())) {
            return null;
        }

        user.setUsername(queryedUser.getUsername());
        user.setId(queryedUser.getId());

        return jwtUtil.generateUserToken(queryedUser);
    }
}
