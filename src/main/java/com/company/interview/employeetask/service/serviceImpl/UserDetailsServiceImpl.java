package com.company.interview.employeetask.service.serviceImpl;

import com.company.interview.employeetask.dto.UserDto;
import com.company.interview.employeetask.entity.enums.Role;
import com.company.interview.employeetask.security.UserDetailsImpl;
import com.company.interview.employeetask.utils.CommonMessageBundle;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.Object;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDetailsServiceImpl implements UserDetailsService {

    final UserServiceImpl userServiceImpl;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) {
        UserDto user = userServiceImpl.findByLogin(login);
        return UserDetailsImpl.create(user, Role.USER);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        UserDto user = userServiceImpl.getUser(id);
        return UserDetailsImpl.create(user, Role.USER);
    }
}