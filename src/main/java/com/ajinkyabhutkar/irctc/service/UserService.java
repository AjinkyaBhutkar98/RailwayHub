package com.ajinkyabhutkar.irctc.service;

import com.ajinkyabhutkar.irctc.dto.UserDto;

public interface UserService {

    public UserDto registerUser(UserDto userDto);

    public UserDto registerAdmin(UserDto userDto);

}
