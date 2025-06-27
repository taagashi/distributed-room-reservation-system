package com.br.thaua.employee_service.services.adapters;

import com.br.thaua.employee_service.domain.Employee;
import com.br.thaua.employee_service.messaging.mappers.EmployeeEventMapper;
import com.br.thaua.employee_service.core.messaging.publishers.EmployeeEventPublisherPort;
import com.br.thaua.employee_service.core.repository.EmployeeRepositoryPort;
import com.br.thaua.employee_service.core.services.EmployeeServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceAdapter implements EmployeeServicePort {
    private final EmployeeEventPublisherPort employeeEventPublisherPort;
    private final EmployeeRepositoryPort employeeRepositoryPort;
    private final EmployeeEventMapper employeeEventMapper;

    @Override
    public Employee addNewEmployee(Employee employee) {
        Employee saved = employeeRepositoryPort.save(employee);

        employeeEventPublisherPort.createdEmployee(employeeEventMapper.map(saved));
        return saved;
    }

    @Override
    public Employee updateEmployeeById(Long id, Employee employee) {
        Employee employeeUpdate = employeeRepositoryPort.findById(id);

        employeeUpdate.setName(employee.getName());
        employeeUpdate.setEmail(employee.getEmail());
        employeeUpdate.setDepartment(employee.getDepartment());
        employeeUpdate.setAge(employee.getAge());

        Employee update = employeeRepositoryPort.update(employeeUpdate);

        employeeEventPublisherPort.updateEmployee(employeeEventMapper.map(update));
        return update;
    }

    @Override
    public Employee fetchEmployeeById(Long id) {
        return employeeRepositoryPort.findById(id);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeEventPublisherPort.deletedEmployee(id);
        employeeRepositoryPort.deleteById(id);
    }

}
