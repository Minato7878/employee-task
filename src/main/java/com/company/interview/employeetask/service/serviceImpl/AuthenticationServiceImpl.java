package com.company.interview.employeetask.service.serviceImpl;

import com.company.interview.employeetask.dto.UserDto;
import com.company.interview.employeetask.entity.enums.Role;
import com.company.interview.employeetask.security.JwtTokenProvider;
import com.company.interview.employeetask.utils.CommonMessageBundle;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Slf4j
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationServiceImpl {

    final AuthenticationManager authenticationManager;
    final JwtTokenProvider tokenProvider;
    final UserServiceImpl userServiceImpl;
    final CommonMessageBundle messageBundle;
    final PasswordEncoder passwordEncoder;


    public String login(UserDto user) {
        return authenticate(user.getLogin(), user.getPassword());
    }

    public String register(UserDto user) throws AuthenticationException {
        String login = user.getLogin();
        String password = user.getPassword();
        if (userServiceImpl.loginExists(login)) {
            throw new AuthenticationException(messageBundle.getMessage("user.login.not.unique.exception"));
        }

        if (userServiceImpl.emailExists(user.getEmail())) {
            throw new AuthenticationException(messageBundle.getMessage("user.email.not.unique.exception"));
        }

        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.USER);
        userServiceImpl.createUser(user);

        return authenticate(login, password);
    }

    private String authenticate(String login, String password) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(
                        login, password
                );

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(token);
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw e;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        return "Bearer " + jwt;
    }
}