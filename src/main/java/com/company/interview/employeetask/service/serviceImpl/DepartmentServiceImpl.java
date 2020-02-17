package com.company.interview.employeetask.service.serviceImpl;

import com.company.interview.employeetask.dto.DepartmentDto;
import com.company.interview.employeetask.exception.DepartmentServiceException;
import com.company.interview.employeetask.exception.EntityNotFoundException;
import com.company.interview.employeetask.mapper.DepartmentMapper;
import com.company.interview.employeetask.repository.DepartmentRepository;
import com.company.interview.employeetask.service.DepartmentService;
import com.company.interview.employeetask.utils.CommonMessageBundle;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepartmentServiceImpl implements DepartmentService {

    final DepartmentRepository departmentRepository;
    final DepartmentMapper departmentMapper;
    final CommonMessageBundle messageBundle;

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentDto> getAllDepartments() {
        return departmentMapper.toDtoList(departmentRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentDto getDepartment(Long departmentId) {
        return departmentMapper.toDto(departmentRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(messageBundle.getMessage("department.not.found.exception"), departmentId))
                ));
    }

    @Override
    @Transactional
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        validateDepartment(departmentDto);
        departmentRepository.save(departmentMapper.toEntity(departmentDto));
        return departmentDto;
    }

    @Override
    @Transactional
    public DepartmentDto updateDepartment(DepartmentDto departmentDto, Long departmentId) {
        validateDepartmentExistence(departmentId);
        validateDepartment(departmentDto);
        departmentDto.setId(departmentId);
        departmentRepository.update(departmentMapper.toEntity(departmentDto));
        return departmentDto;
    }

    @Override
    @Transactional
    public void deleteDepartment(Long departmentId) {
        validateDepartmentExistence(departmentId);
        departmentRepository.deleteById(departmentId);
    }

    private void validateDepartment(DepartmentDto departmentDto) {
        if (StringUtils.isEmpty(departmentDto.getName())) {
            throw new DepartmentServiceException(messageBundle.getMessage("department.invalid.name.exception"));
        }
        if (departmentRepository.existByName(departmentDto.getName())) {
            throw new DepartmentServiceException(String.format(
                    messageBundle.getMessage("department.not.unique.name.exception"), departmentDto.getName()));
        }
    }

    private void validateDepartmentExistence(Long departmentId) {
        if (BooleanUtils.isFalse(departmentRepository.existById(departmentId))) {
            throw new DepartmentServiceException(String.format(
                    messageBundle.getMessage("department.not.found.exception"), departmentId));
        }
    }
}
