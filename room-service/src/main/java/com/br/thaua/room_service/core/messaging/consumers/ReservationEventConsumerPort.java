package com.br.thaua.room_service.core.messaging.consumers;

public interface ReservationEventConsumerPort {
    void consumerEvents(String event);
}
