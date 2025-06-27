package com.br.thaua.reservation_service.messaging.adapters.consumers;

import com.br.thaua.reservation_service.core.cache.EmployeeCachePort;
import com.br.thaua.reservation_service.core.messaging.consumers.EmployeeEventConsumerPort;
import com.br.thaua.reservation_service.core.repository.ReservationRepositoryPort;
import com.br.thaua.reservation_service.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeRabbitEventConsumerAdapter implements EmployeeEventConsumerPort {
    private final EmployeeCachePort employeeCachePort;
    private final ReservationRepositoryPort reservationRepositoryPort;

    @RabbitListener(queues = {"employee.created.queue", "employee.update.queue"})
    @Override
    public void fetchEmployeeForMessaging(Employee employee) {
        employeeCachePort.putCacheEmployee(employee);
    }

    @Transactional
    @RabbitListener(queues = "employee.deleted.queue")
    @Override
    public void fetchEmployeeIdDeletedForMessaging(Long employeeId) {
        employeeCachePort.evictEmployee(employeeId);
        reservationRepositoryPort.deleteAllByEmployeeId(employeeId);
    }
}
