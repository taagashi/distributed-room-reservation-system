package com.br.thaua.employee_service.core.repository;

import com.br.thaua.employee_service.domain.Employee;

public interface EmployeeRepositoryPort {
    Employee save(Employee employee);
    Employee update(Employee employee);
    void update(Long employeeId, String email);
    Employee findById(Long id);
    void deleteById(Long id);
    void increaseEmployeeScore(Long employeeId, int score);
    void deCreaseEmployeeScore(Long employeeId, int score);
}
