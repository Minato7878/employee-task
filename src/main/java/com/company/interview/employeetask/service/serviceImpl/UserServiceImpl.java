package com.company.interview.employeetask.service.serviceImpl;

import com.company.interview.employeetask.dto.UserDto;
import com.company.interview.employeetask.mapper.UserMapper;
import com.company.interview.employeetask.repository.UserRepository;
import com.company.interview.employeetask.security.UserDetailsImpl;
import com.company.interview.employeetask.service.UserService;
import com.company.interview.employeetask.utils.CommonMessageBundle;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    final CommonMessageBundle messageBundle;
    final UserRepository userRepository;
    final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public UserDto findByLogin(String login) {
        return userMapper.toDto(userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(messageBundle.getMessage("login.not.found.exception"), login))
                ));
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean loginExists(String login) {
        return userRepository.existsByLogin(login);
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUser(Long id) {
        return userMapper.toDto(userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(messageBundle.getMessage("user.not.found.exception"), id))
                ));
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        userRepository.save(userMapper.toEntity(userDto));
        return userDto;
    }

    @Override
    @Transactional
    public UserDto updateUser(UserDto userDto, Long userId) {
        userDto.setId(userId);
        userRepository.update(userMapper.toEntity(userDto));
        return userDto;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public UserDto getCurrentUser(UserDetailsImpl userDetailsImpl) {
        if (Objects.nonNull(userDetailsImpl)) {
            return userMapper.toDto(userRepository.findById(userDetailsImpl.getId())
                    .orElseThrow(() -> new UsernameNotFoundException(
                            String.format(messageBundle.getMessage("user.not.found.exception"), userDetailsImpl.getId()))
                    ));
        } else {
            return new UserDto();
        }
    }
}