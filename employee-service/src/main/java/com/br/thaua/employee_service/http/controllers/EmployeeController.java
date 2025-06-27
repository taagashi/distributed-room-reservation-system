package com.br.thaua.employee_service.http.controllers;

import com.br.thaua.employee_service.domain.Employee;
import com.br.thaua.employee_service.http.dto.EmployeeRequest;
import com.br.thaua.employee_service.http.dto.EmployeeResponse;
import com.br.thaua.employee_service.core.services.EmployeeServicePort;
import com.br.thaua.employee_service.http.mappers.EmployeeDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeServicePort employeeServicePort;
    private final EmployeeDtoMapper employeeDtoMapper;

    @PostMapping
    public ResponseEntity<EmployeeResponse> addNewEmployee(@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeDtoMapper.map(employeeRequest);
        EmployeeResponse employeeResponse = employeeDtoMapper.map(employeeServicePort.addNewEmployee(employee));
        return ResponseEntity.ok(employeeResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> fetchEmployeeById(@PathVariable Long id) {
        EmployeeResponse employeeResponse = employeeDtoMapper.map(employeeServicePort.fetchEmployeeById(id));
        return ResponseEntity.ok(employeeResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployeeById(@PathVariable Long id, @RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeDtoMapper.map(employeeRequest);
        EmployeeResponse employeeResponse = employeeDtoMapper.map(employeeServicePort.updateEmployeeById(id, employee));
        return ResponseEntity.ok(employeeResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long id) {
        employeeServicePort.deleteEmployeeById(id);
        return ResponseEntity.ok().build();
    }

}
