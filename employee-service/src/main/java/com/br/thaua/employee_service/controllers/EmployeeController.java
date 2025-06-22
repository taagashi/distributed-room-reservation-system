package com.br.thaua.employee_service.controllers;

import com.br.thaua.employee_service.controllers.dto.EmployeeRequest;
import com.br.thaua.employee_service.controllers.dto.EmployeeResponse;
import com.br.thaua.employee_service.services.ports.EmployeeServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeServicePort employeeServicePort;

    @PostMapping
    public ResponseEntity<EmployeeResponse> addNewEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return ResponseEntity.ok(employeeServicePort.addNewEmployee(employeeRequest));
    }

    @GetMapping
    public ResponseEntity<EmployeeResponse> fetchEmployeeById(@RequestParam(required = false) Long id, @RequestParam(required = false) String email) {
        if(id != null) {
            return ResponseEntity.ok(employeeServicePort.fetchEmployeeById(id));
        }

        if(email != null) {
            return ResponseEntity.ok(employeeServicePort.fetchEmployeeByEmail(email));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/update")
    public ResponseEntity<EmployeeResponse> updateEmployeeById(@RequestParam(required = false) Long id, @RequestParam(required = false) String email, @RequestBody EmployeeRequest employeeRequest) {
        if(id != null) {
            return ResponseEntity.ok(employeeServicePort.updateEmployeeById(id, employeeRequest));
        }

        if(email != null) {
            return ResponseEntity.ok(employeeServicePort.updateEmployeeByEmail(email, employeeRequest));
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
