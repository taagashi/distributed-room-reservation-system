package com.br.thaua.employee_service.core.repository;

import com.br.thaua.employee_service.domain.Employee;

public interface EmployeeRepositoryPort {
    Employee save(Employee employee);
    Employee update(Employee employee);
    Employee findById(Long id);
    void deleteById(Long id);
}
