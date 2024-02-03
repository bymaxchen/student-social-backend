package com.group1.studentsocialbackend.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class CookieUtil {
    public static Cookie generateCookie(String name, String value, String path) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true); // Enhance security by making the cookie inaccessible to JavaScript
        cookie.setPath(path); // Cookie is accessible on all paths
        return cookie;
    }

    public static String getCookieValue(Cookie[] cookies, String name) {
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }

        return null;
    }
}
