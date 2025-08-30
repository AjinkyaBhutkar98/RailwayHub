package com.ajinkyabhutkar.irctc.controller;

import com.ajinkyabhutkar.irctc.config.security.JwtHelper;
import com.ajinkyabhutkar.irctc.dto.ErrorResponse;
import com.ajinkyabhutkar.irctc.dto.JwtResponse;
import com.ajinkyabhutkar.irctc.dto.LoginRequest;
import com.ajinkyabhutkar.irctc.dto.UserDto;
import com.ajinkyabhutkar.irctc.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private UserDetailsService userDetailsService;

    private JwtHelper jwtHelper;

    private UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtHelper jwtHelper,UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtHelper = jwtHelper;
        this.userService=userService;
    }

    @PostMapping("/login")
    @Operation(summary = "login and get token", description = "this api is for generating token for user/admin")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {


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
                    "401",
                    false
            );


            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        }


    }
    //NORMAL user register
    @PostMapping("/register")
    @Operation(summary = "create new user", description = "this api is for registering new user")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto){

        return new ResponseEntity<>(userService.registerUser(userDto),HttpStatus.CREATED);
    }

    //admin
    @PostMapping("/admin/register")
    @Operation(summary = "create new admin", description = "this api is for registering new admin")
    public ResponseEntity<UserDto> registerAdmin(@Valid @RequestBody UserDto userDto){

        return new ResponseEntity<>(userService.registerAdmin(userDto),HttpStatus.CREATED);
    }

    @GetMapping("/test")
    @PreAuthorize("hasRole('NORMAL')")
    public String test(){
        return "testing";
    }

}
