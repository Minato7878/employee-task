package com.company.interview.employeetask.service.serviceImpl;

import com.company.interview.employeetask.dto.EmployeeDto;
import com.company.interview.employeetask.dto.EmployeeViewDto;
import com.company.interview.employeetask.exception.EmployeeServiceException;
import com.company.interview.employeetask.exception.EntityNotFoundException;
import com.company.interview.employeetask.mapper.EmployeeMapper;
import com.company.interview.employeetask.repository.DepartmentRepository;
import com.company.interview.employeetask.repository.EmployeeRepository;
import com.company.interview.employeetask.service.EmployeeService;
import com.company.interview.employeetask.utils.CommonMessageBundle;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeServiceImpl implements EmployeeService {

    final DepartmentRepository departmentRepository;
    final EmployeeRepository employeeRepository;
    final CommonMessageBundle messageBundle;
    final EmployeeMapper employeeMapper;

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDto> getAllEmployees() {
        return employeeMapper.toDtoList(employeeRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeViewDto> getAllEmployeesView(String filter, String sortDirection, Pageable pageable) {
        return employeeRepository.findAllPageableView(filter, sortDirection, pageable);
    }

    @Override
    @Transactional
    public Integer getAllEmployeesCount() {
        return employeeRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDto getEmployee(Long employeeId) {
        return employeeMapper.toDto(employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(messageBundle.getMessage("employee.not.found.exception"), employeeId))
                ));
    }

    @Override
    @Transactional
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        validateEmployee(employeeDto);
        employeeRepository.save(employeeMapper.toEntity(employeeDto));
        return employeeDto;
    }

    @Override
    @Transactional
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long employeeId) {
        validateEmployeeExistence(employeeId);
        employeeDto.setId(employeeId);
        validateEmployee(employeeDto);
        employeeRepository.update(employeeMapper.toEntity(employeeDto));
        return employeeDto;
    }

    @Override
    @Transactional
    public void deleteEmployee(Long employeeId) {
        validateEmployeeExistence(employeeId);
        employeeRepository.deleteById(employeeId);
    }

    private void validateEmployee(EmployeeDto employeeDto) {
        if (StringUtils.isEmpty(employeeDto.getName())) {
            throw new EmployeeServiceException(messageBundle.getMessage("employee.invalid.name.exception"));
        }
        if (employeeRepository.existByName(employeeDto.getId(), employeeDto.getName())) {
            throw new EmployeeServiceException(String.format(
                    messageBundle.getMessage("employee.not.unique.name.exception"), employeeDto.getName()));
        }
        if (Objects.nonNull(employeeDto.getDepartmentId())
                && BooleanUtils.isFalse(departmentRepository.existById(employeeDto.getDepartmentId()))) {
            throw new EmployeeServiceException(String.format(
                    messageBundle.getMessage("department.not.found.exception"), employeeDto.getDepartmentId()));
        }
    }

    private void validateEmployeeExistence(Long employeeId) {
        if (BooleanUtils.isFalse(employeeRepository.existById(employeeId))) {
            throw new EmployeeServiceException(String.format(
                    messageBundle.getMessage("employee.not.found.exception"), employeeId));
        }
    }
}
