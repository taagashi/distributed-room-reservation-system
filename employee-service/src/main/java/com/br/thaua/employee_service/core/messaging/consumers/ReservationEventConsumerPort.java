package com.br.thaua.employee_service.core.messaging.consumers;

public interface ReservationEventConsumerPort {
    void consumerEvents(String event);
}
