package com.company.interview.employeetask.repository;

import com.company.interview.employeetask.dto.EmployeeViewDto;
import com.company.interview.employeetask.entity.Employee;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    Integer count();

    int save(Employee employee);

    int update(Employee employee);

    int deleteById(Long id);

    List<Employee> findAll();

    List<EmployeeViewDto> findAllPageableView(String filter, String sortDirection, Pageable pageable);

    Optional<Employee> findById(Long id);

    Boolean existById(Long employeeId);

    Boolean existByName(Long id, String name);
}
