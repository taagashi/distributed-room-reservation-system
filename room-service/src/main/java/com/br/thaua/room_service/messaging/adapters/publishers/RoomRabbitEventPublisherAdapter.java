package com.br.thaua.room_service.messaging.adapters.publishers;

import com.br.thaua.room_service.core.messaging.publishers.RoomEventPublisherPort;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomRabbitEventPublisherAdapter implements RoomEventPublisherPort {
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendToRoomExchange(Object payload) {
        rabbitTemplate.convertAndSend(payload);
    }
}
