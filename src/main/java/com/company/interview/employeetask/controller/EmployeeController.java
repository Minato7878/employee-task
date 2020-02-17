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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/employee")
@CrossOrigin("http://localhost:4200")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeController {

    private static final String LOG_MESSAGE = "Endpoint - {}() call";
    private static final String LOG_MESSAGE_WITH_EMPLOYEE_ID = "Endpoint - {}({}) call";

    final EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        log.debug(LOG_MESSAGE, "getAllEmployees");
        return new ResponseEntity<>((employeeService.getAllEmployees()), HttpStatus.OK);
    }

    @GetMapping("/all/view")
    public ResponseEntity<List<EmployeeViewDto>> getAllEmployeesView(@RequestParam String filter,
                                                                     @RequestParam String sortDirection,
                                                                     @PageableDefault(size = 5) Pageable pageable) {
        log.debug(LOG_MESSAGE, "getAllEmployeesView");
        return new ResponseEntity<>((employeeService.getAllEmployeesView(filter, sortDirection, pageable)), HttpStatus.OK);
    }

    @GetMapping("/all/count")
    public ResponseEntity<Integer> getAllEmployeesCount() {
        log.debug(LOG_MESSAGE, "getAllEmployeesCount");
        return new ResponseEntity<>((employeeService.getAllEmployeesCount()), HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable @ValidId Long employeeId) {
        log.debug(LOG_MESSAGE_WITH_EMPLOYEE_ID, "getEmployee", employeeId);
        return new ResponseEntity<>(employeeService.getEmployee(employeeId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        log.debug(LOG_MESSAGE, "createEmployee");
        return new ResponseEntity<>(employeeService.createEmployee(employeeDto), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto,
                                                      @RequestParam @ValidId Long employeeId) {
        log.debug(LOG_MESSAGE_WITH_EMPLOYEE_ID, "updateEmployee", employeeId);
        return new ResponseEntity<>(employeeService.updateEmployee(employeeDto, employeeId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{employeeId}")
    public void deleteEmployeeById(@PathVariable @ValidId Long employeeId) {
        log.debug(LOG_MESSAGE_WITH_EMPLOYEE_ID, "deleteEmployee", employeeId);
        employeeService.deleteEmployee(employeeId);
    }
}
