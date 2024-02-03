package com.group1.studentsocialbackend.util;

import com.alibaba.fastjson2.JSONObject;
import com.group1.studentsocialbackend.PO.User;
import com.group1.studentsocialbackend.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;


@Component
public class JwtUtil {

    @Autowired
    private JwtConfig jwtConfig;
    private final Key key;
    @Autowired
    public JwtUtil(JwtConfig jwtConfig) {
        // Initialize the key using your secret string.
        this.key = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String payload, long expirationTime) {
        return Jwts.builder()
                .setSubject(payload)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)
                .compact();
    }

    /**
     * genereate a user jwt
     * @param user
     * @return
     */
    public String generateUserToken(User user) {
        if (user == null) {
            return null;
        }

        user.setPassword("");

        return generateToken(JSONObject.toJSONString(user), jwtConfig.getExpiration());
    }

    // Method to parse the JWT token
    public String extractUsername(String token) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public Boolean validateToken(String token, String username) {
        final String usernameFromToken = extractUsername(token);
        return (usernameFromToken.equals(username) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}
