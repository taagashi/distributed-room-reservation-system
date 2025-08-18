package com.br.thaua.reservation_service.messaging.adapters.publishers;

import com.br.thaua.reservation_service.core.messaging.publishers.ReservationEventPublisherPort;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationEventPublisherAdapter implements ReservationEventPublisherPort {
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendToReservationExchange(Object payload) {
        rabbitTemplate.convertAndSend(payload);
    }
}
