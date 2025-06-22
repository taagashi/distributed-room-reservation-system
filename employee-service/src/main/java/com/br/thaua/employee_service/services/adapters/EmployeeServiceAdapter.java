package com.br.thaua.employee_service.services.adapters;

import com.br.thaua.employee_service.controllers.dto.EmployeeRequest;
import com.br.thaua.employee_service.controllers.dto.EmployeeResponse;
import com.br.thaua.employee_service.mappers.EmployeeMapper;
import com.br.thaua.employee_service.mappers.EmployeeEventMapper;
import com.br.thaua.employee_service.messaging.ports.EmployeeEventPublisherPort;
import com.br.thaua.employee_service.models.EmployeeEntity;
import com.br.thaua.employee_service.repositories.ports.EmployeeRepositoryPort;
import com.br.thaua.employee_service.services.ports.EmployeeServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceAdapter implements EmployeeServicePort {
    private final EmployeeEventPublisherPort employeeEventPublisherPort;
    private final EmployeeRepositoryPort employeeRepositoryPort;
    private final EmployeeEventMapper employeeEventMapper;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeResponse addNewEmployee(EmployeeRequest employeeRequest) {
        EmployeeEntity employeeEntity = employeeMapper.map(employeeRequest);
        EmployeeEntity saved = employeeRepositoryPort.save(employeeEntity);

        employeeEventPublisherPort.createdEmployee(employeeEventMapper.map(saved));
        return employeeMapper.map(saved);
    }

    @Override
    public EmployeeResponse updateEmployeeById(Long id, EmployeeRequest employeeRequest) {
        EmployeeEntity employeeEntityUpdate = employeeRepositoryPort.findById(id);

        employeeEntityUpdate.setNome(employeeRequest.name());
        employeeEntityUpdate.setEmail(employeeRequest.email());
        employeeEntityUpdate.setDepartamento(employeeRequest.department());
        employeeEntityUpdate.setAge(employeeRequest.age());

        EmployeeEntity update = employeeRepositoryPort.save(employeeEntityUpdate);

        employeeEventPublisherPort.updateEmployee(employeeEventMapper.map(update));
        return employeeMapper.map(update);
    }

    @Override
    public EmployeeResponse updateEmployeeByEmail(String email, EmployeeRequest employeeRequest) {
        EmployeeEntity employeeEntityUpdate = employeeRepositoryPort.findByEmail(email);

        employeeEntityUpdate.setNome(employeeRequest.name());
        employeeEntityUpdate.setEmail(employeeRequest.email());
        employeeEntityUpdate.setDepartamento(employeeRequest.department());
        employeeEntityUpdate.setAge(employeeRequest.age());

        EmployeeEntity update = employeeRepositoryPort.update(employeeEntityUpdate);

        employeeEventPublisherPort.updateEmployee(employeeEventMapper.map(update));
        return employeeMapper.map(update);
    }

    @Override
    public EmployeeResponse fetchEmployeeById(Long id) {
        return employeeMapper.map(employeeRepositoryPort.findById(id));
    }

    @Override
    public EmployeeResponse fetchEmployeeByEmail(String email) {
        return employeeMapper.map(employeeRepositoryPort.findByEmail(email));
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeEventPublisherPort.deletedEmployee(id);
        employeeRepositoryPort.deleteById(id);
    }

    @Override
    public void deleteEmployeeByEmail(String email) {
        employeeEventPublisherPort.deletedEmployee(email);
        employeeRepositoryPort.deleteByEmail(email);
    }
}
