package com.company.interview.employeetask.service;

import com.company.interview.employeetask.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {

    List<DepartmentDto> getAllDepartments();

    DepartmentDto createDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartment(Long id);

    DepartmentDto updateDepartment(DepartmentDto departmentDto, Long id);

    void deleteDepartment(Long id);
}
