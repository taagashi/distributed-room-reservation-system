package com.br.thaua.employee_service.repositories.ports;

import com.br.thaua.employee_service.models.EmployeeEntity;

public interface EmployeeRepositoryPort {
    EmployeeEntity save(EmployeeEntity employeeEntity);
    EmployeeEntity update(EmployeeEntity employeeEntity);
    EmployeeEntity findById(Long id);
    EmployeeEntity findByEmail(String email);
    void deleteById(Long id);
    void deleteByEmail(String email);
}
