package com.company.interview.employeetask.controller;

import com.company.interview.employeetask.custom.validator.ValidId;
import com.company.interview.employeetask.dto.EmployeeDto;
import com.company.interview.employeetask.dto.EmployeeViewDto;
import com.company.interview.employeetask.service.EmployeeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping("/employees")
@CrossOrigin("http://localhost:4200")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeController {

    private static final String LOG_MESSAGE = "Endpoint - {}() call";
    private static final String LOG_MESSAGE_WITH_EMPLOYEE_ID = "Endpoint - {}({}) call";

    final EmployeeService employeeService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        log.debug(LOG_MESSAGE, "getAllEmployees");
        return new ResponseEntity<>((employeeService.getAllEmployees()), HttpStatus.OK);
    }

    @GetMapping("/view")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<EmployeeViewDto>> getAllEmployeesView(@RequestParam String filter,
                                                                     @RequestParam String sortDirection,
                                                                     @PageableDefault(size = 5) Pageable pageable) {
        log.debug(LOG_MESSAGE, "getAllEmployeesView");
        return new ResponseEntity<>((employeeService.getAllEmployeesView(filter, sortDirection, pageable)), HttpStatus.OK);
    }

    @GetMapping("/count")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Integer> getAllEmployeesCount() {
        log.debug(LOG_MESSAGE, "getAllEmployeesCount");
        return new ResponseEntity<>((employeeService.getAllEmployeesCount()), HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable @ValidId Long employeeId) {
        log.debug(LOG_MESSAGE_WITH_EMPLOYEE_ID, "getEmployee", employeeId);
        return new ResponseEntity<>(employeeService.getEmployee(employeeId), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        log.debug(LOG_MESSAGE, "createEmployee");
        return new ResponseEntity<>(employeeService.createEmployee(employeeDto), HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody @Valid EmployeeDto employeeDto,
                                                      @RequestParam @ValidId Long employeeId) {
        log.debug(LOG_MESSAGE_WITH_EMPLOYEE_ID, "updateEmployee", employeeId);
        return new ResponseEntity<>(employeeService.updateEmployee(employeeDto, employeeId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{employeeId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void deleteEmployeeById(@PathVariable @ValidId Long employeeId) {
        log.debug(LOG_MESSAGE_WITH_EMPLOYEE_ID, "deleteEmployee", employeeId);
        employeeService.deleteEmployee(employeeId);
    }
}
