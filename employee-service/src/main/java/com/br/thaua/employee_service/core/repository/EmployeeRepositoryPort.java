package com.br.thaua.employee_service.core.repository;

import com.br.thaua.employee_service.domain.Employee;

public interface EmployeeRepositoryPort {
    Employee save(Employee employee);
    Employee update(Employee employee);
    Employee findById(Long id);
    Employee findByEmail(String email);
    void deleteById(Long id);
    void deleteByEmail(String email);
}
