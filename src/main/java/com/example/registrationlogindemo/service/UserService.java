package com.example.registrationlogindemo.service;



import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.UserEntity;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    UserEntity findUserByUsername(String username);

    List<UserDto> findAllUsers();
}