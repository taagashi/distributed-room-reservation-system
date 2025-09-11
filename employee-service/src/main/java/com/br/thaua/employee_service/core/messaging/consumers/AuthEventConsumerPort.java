package com.br.thaua.employee_service.core.messaging.consumers;

public interface AuthEventConsumerPort {
    void consumerEvents(String event);
}
