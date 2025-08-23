package com.ajinkyabhutkar.irctc.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtHelper {

    //generate token

    private final static long JWT_VALIDITY= 60 * 60 * 1000; // 5 minutes

    //hardcoded key
    private final String SECRET_KEY = "8J7xjR7k8fZy1tQwT6Xg+2wQYJ5hXvaxmr+0N9e4m7o0OGrwY9uN0r7+VzQqP0w2s0Z4V3vN6Qy4Xx1j7";

    //way to generate key
    private Key key;

    @PostConstruct
    public void init(){

          this.key= Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(UserDetails userDetails){

        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+JWT_VALIDITY))
                .signWith(key, SignatureAlgorithm.HS512).compact();
    }

    //get username from token

    public String getUsernameFromToken(String token){

        return getClaims(token).getSubject();
    }

    //get all claims from token
    private Claims getClaims(String token) {

        return Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    //validate token
    public boolean isTokenValid(String token,UserDetails userDetails){

        String userName=getUsernameFromToken(token);

        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    //check token expiration
    private boolean isTokenExpired(String token) {

        return getClaims(token).getExpiration().before(new Date());
    }

    //refresh token info added later

}
