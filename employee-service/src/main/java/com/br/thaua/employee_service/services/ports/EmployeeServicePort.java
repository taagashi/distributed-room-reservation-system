package com.br.thaua.employee_service.services.ports;

import com.br.thaua.employee_service.controllers.dto.EmployeeRequest;
import com.br.thaua.employee_service.controllers.dto.EmployeeResponse;

public interface EmployeeServicePort {
    EmployeeResponse addNewEmployee(EmployeeRequest employeeRequest);
    EmployeeResponse updateEmployeeById(Long id, EmployeeRequest employeeRequest);
    EmployeeResponse updateEmployeeByEmail(String email, EmployeeRequest employeeRequest);
    EmployeeResponse fetchEmployeeById(Long id);
    EmployeeResponse fetchEmployeeByEmail(String email);
    void deleteEmployeeById(Long id);
    void deleteEmployeeByEmail(String email);
}
