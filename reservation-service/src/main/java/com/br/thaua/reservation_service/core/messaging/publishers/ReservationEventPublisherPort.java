package com.br.thaua.reservation_service.core.messaging.publishers;

public interface ReservationEventPublisherPort {
    void sendToReservationExchange(Object payload);
}
