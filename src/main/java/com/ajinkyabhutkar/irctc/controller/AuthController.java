package com.ajinkyabhutkar.irctc.controller;

import com.ajinkyabhutkar.irctc.config.security.JwtHelper;
import com.ajinkyabhutkar.irctc.dto.ErrorResponse;
import com.ajinkyabhutkar.irctc.dto.JwtResponse;
import com.ajinkyabhutkar.irctc.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private UserDetailsService userDetailsService;

    private JwtHelper jwtHelper;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtHelper jwtHelper) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtHelper = jwtHelper;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest loginRequest
    ) {


        //token generate code
        try {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());

            this.authenticationManager.authenticate(authentication);

            // generate token
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.username());
            String token = this.jwtHelper.generateToken(userDetails);
            JwtResponse jwtResponse = new JwtResponse(
                    token,
                    userDetails.getUsername()
            );

            return new ResponseEntity<>(jwtResponse, org.springframework.http.HttpStatus.OK);


        } catch (BadCredentialsException ex) {
            System.out.println("Invalid Credentials");
            ErrorResponse errorResponse = new ErrorResponse(
                    "The username or password you entered is incorrect.",
                    "400",
                    false
            );


            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }


    }

}
