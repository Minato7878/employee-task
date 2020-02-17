package com.company.interview.employeetask.controller;

import com.company.interview.employeetask.dto.SignInFormDto;
import com.company.interview.employeetask.dto.SignUpFormDto;
import com.company.interview.employeetask.dto.UserDto;
import com.company.interview.employeetask.mapper.SignInFormMapper;
import com.company.interview.employeetask.mapper.SignUpFormMapper;
import com.company.interview.employeetask.security.AuthenticationConstant;
import com.company.interview.employeetask.service.serviceImpl.AuthenticationServiceImpl;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/authentication")
@CrossOrigin("http://localhost:4200")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationController {

    @Value("${app.cookieExpirationInS}")
    int cookieExpiration;

    final AuthenticationServiceImpl authenticationService;

    final SignInFormMapper signInFormMapper;

    final SignUpFormMapper signUpFormMapper;

    @Autowired
    public AuthenticationController(AuthenticationServiceImpl authenticationService,
                                    SignInFormMapper signInFormMapper, SignUpFormMapper signUpFormMapper) {
        this.authenticationService = authenticationService;
        this.signInFormMapper = signInFormMapper;
        this.signUpFormMapper = signUpFormMapper;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(HttpServletResponse response,
                                              @Valid @RequestBody SignInFormDto signInFormDTO) {
        UserDto user = signInFormMapper.toEntity(signInFormDTO);
        String token = authenticationService.login(user);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(AuthenticationConstant
                .AUTHENTICATION_TOKEN_HEADER, token);
        response.addCookie(createCookie(token));
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(HttpServletResponse response,
                                          @Valid @RequestBody SignUpFormDto signUpFormDTO)
            throws AuthenticationException {

        UserDto user = signUpFormMapper.toEntity(signUpFormDTO);
        String token = authenticationService.register(user);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(AuthenticationConstant
                .AUTHENTICATION_TOKEN_HEADER, token);
        response.addCookie(createCookie(token));
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(token);
    }

    private Cookie createCookie(String token) {
        final Cookie cookie = new Cookie(AuthenticationConstant
                .AUTHENTICATION_TOKEN_HEADER, token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(cookieExpiration);
        return cookie;
    }
}