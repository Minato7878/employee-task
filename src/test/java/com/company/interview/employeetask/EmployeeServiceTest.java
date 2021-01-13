package com.company.interview.employeetask;

import com.company.interview.employeetask.dto.EmployeeDto;
import com.company.interview.employeetask.repository.EmployeeRepository;
import com.company.interview.employeetask.service.EmployeeService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Before
    public void setup() {
        doNothing().when(employeeRepository.update(any()));
    }

    @Test
    public void testUpdateEmployee() {
        List<EmployeeDto> employeeDtoList = preperaEmployees();
        EmployeeDto expectedEmployeeDto = employeeDtoList.get(1);

        when(employeeRepository.existById(1L)).thenReturn(true);

        EmployeeDto actualEmployeeDto = employeeService.updateEmployee(expectedEmployeeDto, 1L);
        Assertions.assertEquals(actualEmployeeDto, expectedEmployeeDto);
    }

    private List<EmployeeDto> preperaEmployees() {
        EmployeeDto emp1 = EmployeeDto.builder()
                .id(1L)
                .name("Micki Bitting")
                .isActive(true)
                .departmentId(1L)
                .build();

        EmployeeDto emp2 = EmployeeDto.builder()
                .id(2L)
                .name("Virgie Repp")
                .isActive(true)
                .departmentId(1L)
                .build();

        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        employeeDtoList.add(emp1);
        employeeDtoList.add(emp2);

        return employeeDtoList;
    }
}
