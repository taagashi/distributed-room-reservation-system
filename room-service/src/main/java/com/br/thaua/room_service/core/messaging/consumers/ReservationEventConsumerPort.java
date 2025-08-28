package com.br.thaua.room_service.core.messaging.consumers;

public interface ReservationEventConsumerPort {
    void consumeEvent(Object event);
}
