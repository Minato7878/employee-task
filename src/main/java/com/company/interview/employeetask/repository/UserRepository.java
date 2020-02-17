package com.company.interview.employeetask.repository;

import com.company.interview.employeetask.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {

    Optional<User> findByLogin(String login);

    Boolean existsByLogin(String login);

    Boolean existsByEmail(String email);

    List<User> findAll();

    Optional<User> findById(Long id);

    int save(User user);

    int update(User user);

    int deleteById(Long id);
}