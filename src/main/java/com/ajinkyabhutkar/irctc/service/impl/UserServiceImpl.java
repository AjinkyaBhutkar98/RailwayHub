package com.ajinkyabhutkar.irctc.service.impl;

import com.ajinkyabhutkar.irctc.dto.UserDto;
import com.ajinkyabhutkar.irctc.entity.Roles;
import com.ajinkyabhutkar.irctc.entity.User;
import com.ajinkyabhutkar.irctc.repo.RoleRepo;
import com.ajinkyabhutkar.irctc.repo.UserRepo;
import com.ajinkyabhutkar.irctc.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    private RoleRepo roleRepo;

    private ModelMapper modelMapper;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto registerUser(UserDto userDto) {

        User user=modelMapper.map(userDto,User.class);
        Roles role=roleRepo.findByName("ROLE_NORMAL").orElseThrow(()->new RuntimeException("something unexcepted happened please contact support team"));
        //roles is an list so we can use list methods
        user.getRoles().add(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User user1=userRepo.save(user);
        return modelMapper.map(user1,UserDto.class);
    }

    @Override
    public UserDto registerAdmin(UserDto userDto) {

        User user=modelMapper.map(userDto,User.class);

        if(userRepo.existsByEmail(userDto.getEmail())){
            throw new RuntimeException("email already exists please check your login details!");
        }

        Roles role=roleRepo.findByName("ROLE_ADMIN").orElseThrow(()->new RuntimeException("something unexcepted happened please contact support team"));
        //roles is an list so we can use list methods
        user.getRoles().add(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User user1=userRepo.save(user);
        return modelMapper.map(user1,UserDto.class);
    }


}
