package com.br.thaua.employee_service.persistence.repositories.adapters;

import com.br.thaua.employee_service.domain.Employee;
import com.br.thaua.employee_service.persistence.mappers.EmployeeMapper;
import com.br.thaua.employee_service.persistence.models.EmployeeEntity;
import com.br.thaua.employee_service.persistence.repositories.EmployeeRepository;
import com.br.thaua.employee_service.core.repository.EmployeeRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeRepositoryAdapter implements EmployeeRepositoryPort {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public Employee save(Employee employee) {
        EmployeeEntity saved = employeeMapper.map(employee);
        return employeeMapper.map(employeeRepository.save(saved));
    }

    @Override
    public Employee update(Employee employee) {
        EmployeeEntity updated = employeeMapper.map(employee);
        return employeeMapper.map(employeeRepository.save(updated));
    }

    @Override
    public Employee findById(Long id) {
        return employeeMapper.map(employeeRepository.findById(id).orElse(null));
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

}
