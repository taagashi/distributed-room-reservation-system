package com.br.thaua.reservation_service.core.messaging.consumers;

public interface AuthEventConsumerPort {
    void consumerAuthEvent(String message);
}
