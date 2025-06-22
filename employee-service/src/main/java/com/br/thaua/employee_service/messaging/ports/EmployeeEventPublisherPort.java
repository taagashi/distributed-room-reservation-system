package com.br.thaua.employee_service.messaging.ports;

public interface EmployeeEventPublisherPort {
    void createdEmployee(Object payload);
    void updateEmployee(Object payload);
    void deletedEmployee(Object payload);
}