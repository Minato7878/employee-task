package com.company.interview.employeetask.repository.jdbcImpl;

import com.company.interview.employeetask.entity.User;
import com.company.interview.employeetask.entity.enums.Role;
import com.company.interview.employeetask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcUserRepository implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByLogin(String login) {
        try {
            return jdbcTemplate.queryForObject(
                    "select * from users where login = ?",
                    new Object[]{login},
                    (rs, rowNum) ->
                            Optional.of(new User(
                                    rs.getLong("id"),
                                    rs.getString("login"),
                                    rs.getString("pass"),
                                    rs.getString("first_name"),
                                    rs.getString("last_name"),
                                    rs.getString("email"),
                                    Role.valueOf(rs.getString("user_role"))
                            ))
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existsByLogin(String login) {
        return jdbcTemplate
                .queryForObject("select count(*)>0 from users where login = ?",
                        new Object[]{login},
                        Boolean.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existsByEmail(String email) {
        return jdbcTemplate
                .queryForObject("select count(*)>0 from users where email = ?",
                        new Object[]{email},
                        Boolean.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return jdbcTemplate.query(
                "select * from users",
                (rs, rowNum) ->
                        new User(
                                rs.getLong("id"),
                                rs.getString("login"),
                                rs.getString("pass"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("email"),
                                Role.valueOf(rs.getString("user_role"))
                        )
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        try {
            return jdbcTemplate.queryForObject(
                    "select * from users where id = ?",
                    new Object[]{id},
                    (rs, rowNum) ->
                            Optional.of(new User(
                                    rs.getLong("id"),
                                    rs.getString("login"),
                                    rs.getString("pass"),
                                    rs.getString("first_name"),
                                    rs.getString("last_name"),
                                    rs.getString("email"),
                                    Role.valueOf(rs.getString("user_role"))
                            ))
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public int save(User user) {
        return jdbcTemplate.update(
                "insert into users (login, pass, first_name, last_name, email, user_role) values(?,?,?,?,?,?)",
                user.getLogin(), user.getPassword(), user.getFirstName(),
                user.getLastName(), user.getEmail(), user.getRole().toString());
    }

    @Override
    @Transactional
    public int update(User user) {
        return jdbcTemplate.update(
                "update users set login = ?, pass = ?, first_name = ?," +
                        " last_name = ?, email = ?, user_role = ? where id = ?",
                user.getLogin(), user.getPassword(), user.getFirstName(),
                user.getLastName(), user.getEmail(), user.getRole(), user.getId());
    }

    @Override
    @Transactional
    public int deleteById(Long id) {
        return jdbcTemplate.update(
                "delete from users where id = ?",
                id);
    }
}
