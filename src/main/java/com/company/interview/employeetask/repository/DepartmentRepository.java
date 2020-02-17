package com.company.interview.employeetask.repository;

import com.company.interview.employeetask.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository {

    int save(Department department);

    int update(Department department);

    int deleteById(Long id);

    List<Department> findAll();

    Optional<Department> findById(Long id);

    Boolean existByName(String name);

    Boolean existById(Long id);
}
