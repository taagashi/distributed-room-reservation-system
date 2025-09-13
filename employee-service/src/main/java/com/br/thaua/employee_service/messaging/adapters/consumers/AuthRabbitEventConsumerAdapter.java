package com.br.thaua.employee_service.messaging.adapters.consumers;

import com.br.thaua.employee_service.core.messaging.consumers.AuthEventConsumerPort;
import com.br.thaua.employee_service.core.services.EmployeeServicePort;
import com.br.thaua.employee_service.domain.Employee;
import com.br.thaua.employee_service.domain.EventType;
import com.br.thaua.employee_service.messaging.dto.AuthEvent;
import com.br.thaua.employee_service.messaging.mappers.AuthEventMapper;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthRabbitEventConsumerAdapter implements AuthEventConsumerPort {
    private final EventPayloadConverter eventPayloadConverter;
    private final EmployeeServicePort employeeServicePort;
    private final AuthEventMapper authEventMapper;

    @RabbitListener(queues = "employee.auth.queue")
    @Override
    public void consumerEvents(String event) {
        JsonNode node = eventPayloadConverter.fetchJsonNode(event);
        if(node == null) return;
        EventType eventType = eventPayloadConverter.fetchEventType(node);
        AuthEvent authEvent = eventPayloadConverter.fetchEventClass(AuthEvent.class, node);

        if(authEvent == null) return;
        Employee employee = authEventMapper.map(authEvent);

        switch(eventType) {
            case AUTH_CREATED -> employeeCreated(employee);
            case AUTH_UPDATED -> employeeUpdated(employee);
            case AUTH_DELETED -> employeeDeleted(employee);

        }
    }

    private void employeeCreated(Employee employee) {
        employeeServicePort.addEmployee(employee);
    }

    private void employeeUpdated(Employee employee) {
        employeeServicePort.updateEmployee(employee);
    }

    private void employeeDeleted(Employee employee) {
        employeeServicePort.deleteEmployee(employee);
    }
}
