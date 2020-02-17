package com.company.interview.employeetask.service;

import com.company.interview.employeetask.dto.EmployeeDto;
import com.company.interview.employeetask.dto.EmployeeViewDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> getAllEmployees();

    List<EmployeeViewDto> getAllEmployeesView(String filter, String sortDirection, Pageable pageable);

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployee(Long id);

    EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id);

    void deleteEmployee(Long id);

    Integer getAllEmployeesCount();
}
