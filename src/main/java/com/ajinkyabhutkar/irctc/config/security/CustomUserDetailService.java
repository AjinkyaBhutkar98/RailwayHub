package com.ajinkyabhutkar.irctc.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

//    @Autowired
//    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails user=User.builder().username("ajinkyabhutkar").password("{noop}ajinkya123").roles("USER").build();

        if(user.getUsername().equals(username)){
            return user;
        }else{
            throw new UsernameNotFoundException("user not found !!"+username);
        }

    }
}
