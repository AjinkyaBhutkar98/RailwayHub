package com.ajinkyabhutkar.irctc.config.security;

import com.ajinkyabhutkar.irctc.entity.User;
import com.ajinkyabhutkar.irctc.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

//    @Autowired
//    private PasswordEncoder passwordEncoder;


    private UserRepo userRepo;

    @Autowired
    public CustomUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        UserDetails user=User.builder().username("ajinkyabhutkar").password("{noop}ajinkya123").roles("USER").build();


        User user=userRepo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not found please register/try with correct details"+username));

        CustomUserDetails customUserDetails=new CustomUserDetails(user);

         return customUserDetails;

    }
}
