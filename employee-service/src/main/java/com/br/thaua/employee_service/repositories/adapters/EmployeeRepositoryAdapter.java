package com.br.thaua.employee_service.repositories.adapters;

import com.br.thaua.employee_service.models.EmployeeEntity;
import com.br.thaua.employee_service.repositories.EmployeeRepository;
import com.br.thaua.employee_service.repositories.ports.EmployeeRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeRepositoryAdapter implements EmployeeRepositoryPort {
    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeEntity save(EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity);
    }

    @Override
    public EmployeeEntity update(EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity);
    }

    @Override
    public EmployeeEntity findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public EmployeeEntity findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void deleteByEmail(String email) {
        employeeRepository.deleteByEmail(email);
    }
}
