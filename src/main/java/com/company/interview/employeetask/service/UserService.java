package com.company.interview.employeetask.service;

import com.company.interview.employeetask.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto findByLogin(String login);

    Boolean loginExists(String login);

    Boolean emailExists(String email);

    List<UserDto> getAllUsers();

    UserDto getUser(Long id);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Long userId);

    void deleteById(Long id);
}
