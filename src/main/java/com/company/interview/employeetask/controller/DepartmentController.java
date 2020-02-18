package com.company.interview.employeetask.controller;

import com.company.interview.employeetask.custom.validator.ValidId;
import com.company.interview.employeetask.dto.DepartmentDto;
import com.company.interview.employeetask.service.DepartmentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")
@RequestMapping("/departments")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepartmentController {

    private static final String LOG_MESSAGE = "Endpoint - {}() call";
    private static final String LOG_MESSAGE_WITH_DEPARTMENT_ID = "Endpoint - {}({}) call";

    final DepartmentService departmentService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        log.debug(LOG_MESSAGE, "getAllDepartments");
        return new ResponseEntity<>((departmentService.getAllDepartments()), HttpStatus.OK);
    }

    @GetMapping("/{departmentId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable @ValidId Long departmentId) {
        log.debug(LOG_MESSAGE_WITH_DEPARTMENT_ID, "getDepartmentById", departmentId);
        return new ResponseEntity<>(departmentService.getDepartment(departmentId), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody @Valid DepartmentDto departmentDto) {
        log.debug(LOG_MESSAGE, "createDepartment");
        return new ResponseEntity<>(departmentService.createDepartment(departmentDto), HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody @Valid DepartmentDto departmentDto,
                                                          @RequestParam @ValidId Long departmentId) {
        log.debug(LOG_MESSAGE_WITH_DEPARTMENT_ID, "updateDepartment", departmentId);
        return new ResponseEntity<>(departmentService.updateDepartment(departmentDto, departmentId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{departmentId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void deleteDepartmentById(@PathVariable @ValidId Long departmentId) {
        log.debug(LOG_MESSAGE_WITH_DEPARTMENT_ID, "deleteDepartment", departmentId);
        departmentService.deleteDepartment(departmentId);
    }
}

