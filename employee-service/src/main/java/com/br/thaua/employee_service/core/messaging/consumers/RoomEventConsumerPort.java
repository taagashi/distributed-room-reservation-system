package com.br.thaua.employee_service.core.messaging.consumers;

public interface RoomEventConsumerPort {
    void consumerEvents(String event);
}
