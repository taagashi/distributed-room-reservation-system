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

    @GetMapping
    public ResponseEntity<EmployeeResponse> fetchEmployeeById(@RequestParam(required = false) Long id, @RequestParam(required = false) String email) {
        if(id != null) {
            EmployeeResponse employeeResponse = employeeDtoMapper.map(employeeServicePort.fetchEmployeeById(id));
            return ResponseEntity.ok(employeeResponse);
        }

        if(email != null) {
            EmployeeResponse employeeResponse = employeeDtoMapper.map(employeeServicePort.fetchEmployeeByEmail(email));
            return ResponseEntity.ok(employeeResponse);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/update")
    public ResponseEntity<EmployeeResponse> updateEmployeeById(@RequestParam(required = false) Long id, @RequestParam(required = false) String email, @RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeDtoMapper.map(employeeRequest);

        if(id != null) {
            EmployeeResponse employeeResponse = employeeDtoMapper.map(employeeServicePort.updateEmployeeById(id, employee));
            return ResponseEntity.ok(employeeResponse);
        }

        if(email != null) {
            EmployeeResponse employeeResponse = employeeDtoMapper.map(employeeServicePort.updateEmployeeByEmail(email, employee));
            return ResponseEntity.ok(employeeResponse);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteEmployeeById(@RequestParam(required = false) Long id, @RequestParam(required = false) String email) {
        if(id != null) {
            employeeServicePort.deleteEmployeeById(id);
            return ResponseEntity.ok().build();
        }

        if(email != null) {
            employeeServicePort.deleteEmployeeByEmail(email);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
