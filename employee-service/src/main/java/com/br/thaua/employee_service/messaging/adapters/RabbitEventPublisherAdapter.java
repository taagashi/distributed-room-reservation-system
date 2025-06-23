package com.br.thaua.employee_service.messaging.adapters;

import com.br.thaua.employee_service.core.messaging.EmployeeEventPublisherPort;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitEventPublisherAdapter implements EmployeeEventPublisherPort {
    private final RabbitTemplate rabbitTemplate;

    @Value("${routing.key.employee.created}")
    private String routingKeyEmployeeCreated;

    @Value("${routing.key.employee.update}")
    private String routingKeyEmployeeUpdate;

    @Value("${routing.key.employee.deleted}")
    private String routingKeyEmployeeDeleted;

    @Value("${exchange.employee.name}")
    private String exchangeEmployeeName;

    @Override
    public void createdEmployee(Object payload) {
        rabbitTemplate.convertAndSend(exchangeEmployeeName, routingKeyEmployeeCreated, payload);
    }

    @Override
    public void updateEmployee(Object payload) {
        rabbitTemplate.convertAndSend(exchangeEmployeeName, routingKeyEmployeeUpdate, payload);
    }

    @Override
    public void deletedEmployee(Object payload) {
        rabbitTemplate.convertAndSend(exchangeEmployeeName, routingKeyEmployeeDeleted, payload);
    }
}
