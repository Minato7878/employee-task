package com.company.interview.employeetask.mapper;

import com.company.interview.employeetask.dto.DepartmentDto;
import com.company.interview.employeetask.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface DepartmentMapper {

    DepartmentDto toDto(Department department);

    Department toEntity(DepartmentDto departmentDto);

    List<DepartmentDto> toDtoList(List<Department> departmentList);

    List<Department> toEntityList(List<DepartmentDto> departmentDtoList);
}
