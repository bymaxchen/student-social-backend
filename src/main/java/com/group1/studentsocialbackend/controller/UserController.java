package com.group1.studentsocialbackend.controller;

import com.group1.studentsocialbackend.PO.Post;
import com.group1.studentsocialbackend.PO.User;
import com.group1.studentsocialbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signin")
    public User signin(@RequestBody User user) {
        String email=user.getEmail();
        //email not exist or login not success
        if(userService.getUserByEmail(email)==null||!user.getPassword().equals(userService.getUserByEmail(email).getPassword())){
            return null;
        }
        else return userService.getUserByEmail(email);
    }
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.createOneUser(user);
    }
}
