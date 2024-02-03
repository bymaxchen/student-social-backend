package com.group1.studentsocialbackend.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class encryptUtil {
    public static String encodePassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
