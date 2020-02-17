package com.company.interview.employeetask.mapper;

import com.company.interview.employeetask.dto.EmployeeDto;
import com.company.interview.employeetask.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface EmployeeMapper {

    EmployeeDto toDto(Employee employee);

    Employee toEntity(EmployeeDto employeeDto);

    List<EmployeeDto> toDtoList(List<Employee> employeeList);

    List<Employee> toEntityList(List<EmployeeDto> employeeDtoList);
}