package com.br.thaua.reservation_service.core.messaging.consumers;

import com.br.thaua.reservation_service.domain.Employee;

public interface EmployeeEventConsumerPort {
    void fetchEmployeeForMessaging(Employee employee);
    void fetchEmployeeIdDeletedForMessaging(Long employeeId);
}
