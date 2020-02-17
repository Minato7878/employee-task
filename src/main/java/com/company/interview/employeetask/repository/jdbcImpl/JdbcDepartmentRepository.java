package com.company.interview.employeetask.repository.jdbcImpl;

import com.company.interview.employeetask.entity.Department;
import com.company.interview.employeetask.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcDepartmentRepository implements DepartmentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public int save(Department department) {
        return jdbcTemplate.update(
                "insert into departments (dep_name) values(?)",
                department.getName());
    }

    @Override
    @Transactional
    public int update(Department department) {
        return jdbcTemplate.update(
                "update departments set dep_name = ? where id = ?",
                department.getName(), department.getId());
    }


    @Override
    @Transactional
    public int deleteById(Long id) {
        return jdbcTemplate.update(
                "delete from departments where id = ?",
                id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Department> findAll() {
        return jdbcTemplate.query(
                "select * from departments",
                (rs, rowNum) ->
                        new Department(
                                rs.getLong("id"),
                                rs.getString("dep_name")
                        )
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Department> findById(Long id) {
        return jdbcTemplate.query(
                "select * from departments where id = ?",
                new Object[]{id},
                rs -> rs.next() ? Optional.of(new Department(
                        rs.getLong("id"),
                        rs.getString("dep_name")
                )) : Optional.empty());
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existByName(String name) {
        return jdbcTemplate
                .queryForObject("select count(*)>0 from departments where dep_name = ?",
                        new Object[]{name},
                        Boolean.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existById(Long id) {
        return jdbcTemplate
                .queryForObject("select count(*)>0 from departments where id = ?",
                        new Object[]{id},
                        Boolean.class);
    }
}
