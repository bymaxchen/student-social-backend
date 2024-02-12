package com.group1.studentsocialbackend.controller;

import com.group1.studentsocialbackend.PO.User;
import com.group1.studentsocialbackend.config.CookieConfig;
import com.group1.studentsocialbackend.service.UserService;
import com.group1.studentsocialbackend.util.CookieUtil;
import com.group1.studentsocialbackend.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    private CookieConfig cookieConfig;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signin")
    public ResponseEntity<User> signin(@RequestBody User user, HttpServletResponse response) {
        String token = userService.signIn(user);

        if (token == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        response.addCookie(CookieUtil.generateCookie(cookieConfig.getName(), token, cookieConfig.getPath()));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletResponse response) {
        response.addCookie(CookieUtil.generateCookie(cookieConfig.getName(), "", cookieConfig.getPath()));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user, HttpServletResponse response) {
        String email=user.getEmail();
        if(userService.getUserByEmail(email)==null){
            User createdUser = userService.createOneUser(user);

            response.addCookie(CookieUtil.generateCookie(cookieConfig.getName(), jwtUtil.generateUserToken(createdUser), cookieConfig.getPath()));

            return new ResponseEntity<>(createdUser, HttpStatus.OK);
        }
        // account has already existed
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
