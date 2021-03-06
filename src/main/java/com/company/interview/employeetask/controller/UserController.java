package com.company.interview.employeetask.controller;

import com.company.interview.employeetask.custom.annotations.CurrentUser;
import com.company.interview.employeetask.custom.validator.ValidId;
import com.company.interview.employeetask.dto.UserDto;
import com.company.interview.employeetask.security.UserDetailsImpl;
import com.company.interview.employeetask.service.serviceImpl.UserServiceImpl;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/users")
@CrossOrigin("http://localhost:4200")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {

    private static final String LOG_MESSAGE = "Endpoint - {}() call";
    private static final String LOG_MESSAGE_WITH_USER_ID = "Endpoint - {}({}) call";
    private static final String LOG_MESSAGE_WITH_USER_LOGIN = "Endpoint - {}({}) call";

    final UserServiceImpl userServiceImpl;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<UserDto>> getAll() {
        log.debug(LOG_MESSAGE, "getAllUsers");
        return new ResponseEntity<>(userServiceImpl.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserDto> getById(@PathVariable @ValidId Long userId) {
        log.debug(LOG_MESSAGE_WITH_USER_ID, "getUserById", userId);
        return new ResponseEntity<>(userServiceImpl.getUser(userId), HttpStatus.OK);
    }

    @GetMapping("/{login}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserDto> getByLogin(@PathVariable String login) {
        log.debug(LOG_MESSAGE_WITH_USER_LOGIN, "getUserByLogin", login);
        return new ResponseEntity<>(userServiceImpl.findByLogin(login), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto) {
        log.debug(LOG_MESSAGE, "createUser");
        return new ResponseEntity<>(userServiceImpl.createUser(userDto), HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserDto userDto,
                                              @RequestParam @ValidId Long userId) {
        log.debug(LOG_MESSAGE_WITH_USER_ID, "updateUser", userId);
        return new ResponseEntity<>(userServiceImpl.updateUser(userDto, userId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void deleteById(@PathVariable @ValidId Long userId) {
        log.debug(LOG_MESSAGE_WITH_USER_ID, "deleteUserById", userId);
        userServiceImpl.deleteById(userId);
    }

    @GetMapping("/me")
    public UserDto getCurrentUser(@CurrentUser UserDetailsImpl userDetailsImpl) {
        log.debug(LOG_MESSAGE, "getCurrentUser");
        return userServiceImpl.getCurrentUser(userDetailsImpl);
    }
}