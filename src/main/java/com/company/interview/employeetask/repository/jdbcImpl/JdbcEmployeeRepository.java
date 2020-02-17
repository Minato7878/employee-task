package com.company.interview.employeetask.repository.jdbcImpl;

import com.company.interview.employeetask.dto.EmployeeViewDto;
import com.company.interview.employeetask.entity.Employee;
import com.company.interview.employeetask.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class JdbcEmployeeRepository implements EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public Integer count() {
        return jdbcTemplate
                .queryForObject("select count(*) from employees", Integer.class);
    }

    @Override
    @Transactional
    public int save(Employee employee) {
        return jdbcTemplate.update(
                "insert into employees (emp_name, emp_active, dep_id) values(?,?,?)",
                employee.getName(), employee.getIsActive().toString(), employee.getDepartmentId());
    }

    @Override
    @Transactional
    public int update(Employee employee) {
        return jdbcTemplate.update(
                "update employees set emp_name = ?, emp_active = ?, dep_id = ? where id = ?",
                employee.getName(), employee.getIsActive().toString(), employee.getDepartmentId(), employee.getId());
    }


    @Override
    @Transactional
    public int deleteById(Long id) {
        return jdbcTemplate.update(
                "delete from employees where id = ?",
                id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return jdbcTemplate.query(
                "select * from employees",
                (rs, rowNum) ->
                        new Employee(
                                rs.getLong("id"),
                                rs.getString("emp_name"),
                                rs.getBoolean("emp_active"),
                                rs.getLong("dep_id")
                        )
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeViewDto> findAllPageableView(String filter, String sortDirection, Pageable pageable) {
        String direction = sortDirection.equalsIgnoreCase("desc") ? "desc" : "asc";
        return jdbcTemplate.query(
                "select employees.id, emp_name, emp_active, departments.dep_name " +
                        "from employees " +
                        "left join departments " +
                        "on departments.id = employees.dep_id " +
                        "where emp_name like ? " +
                        "order by employees.id " + direction + " " +
                        "limit ? " +
                        "offset ?",
                new Object[]{filter + "%", pageable.getPageSize(), pageable.getOffset()},
                (rs, rowNum) ->
                        new EmployeeViewDto(
                                rs.getLong("id"),
                                rs.getString("emp_name"),
                                rs.getBoolean("emp_active"),
                                rs.getString("dep_name")
                        )
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> findById(Long id) {
        try {
            return jdbcTemplate.queryForObject(
                    "select * from employees where id = ?",
                    new Object[]{id},
                    (rs, rowNum) ->
                            Optional.of(new Employee(
                                    rs.getLong("id"),
                                    rs.getString("emp_name"),
                                    rs.getBoolean("emp_active"),
                                    rs.getLong("dep_id")
                            ))
            );
        } catch (
                EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existById(Long employeeId) {
        return jdbcTemplate
                .queryForObject("select count(*)>0 from employees where id = ?",
                        new Object[]{employeeId},
                        Boolean.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existByName(Long id, String employeeName) {
        if (Objects.isNull(id)) id = 0L;
        return jdbcTemplate
                .queryForObject("select count(*)>0 from employees " +
                                "where emp_name = ? and id <> ?",
                        new Object[]{employeeName, id},
                        Boolean.class);
    }
}
