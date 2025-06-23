package com.br.thaua.employee_service.core.services;

import com.br.thaua.employee_service.domain.Employee;

public interface EmployeeServicePort {
    Employee addNewEmployee(Employee employee);
    Employee updateEmployeeById(Long id, Employee employee);
    Employee updateEmployeeByEmail(String email, Employee employee);
    Employee fetchEmployeeById(Long id);
    Employee fetchEmployeeByEmail(String email);
    void deleteEmployeeById(Long id);
    void deleteEmployeeByEmail(String email);
}
