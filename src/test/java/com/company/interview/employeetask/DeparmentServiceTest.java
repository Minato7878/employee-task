package com.company.interview.employeetask;

import com.company.interview.employeetask.dto.DepartmentDto;
import com.company.interview.employeetask.repository.DepartmentRepository;
import com.company.interview.employeetask.service.DepartmentService;
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
public class DeparmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @Before
    public void setup() {
        doNothing().when(departmentRepository.update(any()));
    }

    @Test
    public void testUpdateDepartment() {
        List<DepartmentDto> departmentDtoList = preperaDepartments();
        DepartmentDto expectedDepartmentDto = departmentDtoList.get(1);

        when(departmentRepository.existById(1L)).thenReturn(true);

        DepartmentDto actualDepartmentDto = departmentService.updateDepartment(expectedDepartmentDto, 1L);
        Assertions.assertEquals(actualDepartmentDto, expectedDepartmentDto);
    }

    private List<DepartmentDto> preperaDepartments() {
        DepartmentDto dep1 = DepartmentDto.builder()
                .id(1L)
                .name("Department PZ")
                .build();

        DepartmentDto dep2 = DepartmentDto.builder()
                .id(2L)
                .name("Department KN")
                .build();

        List<DepartmentDto> departmentDtoList = new ArrayList<>();
        departmentDtoList.add(dep1);
        departmentDtoList.add(dep2);

        return departmentDtoList;
    }
}
