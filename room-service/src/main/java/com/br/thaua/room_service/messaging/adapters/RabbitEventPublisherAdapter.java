package com.br.thaua.room_service.messaging.adapters;

import com.br.thaua.room_service.core.messaging.RoomEventPublisherPort;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitEventPublisherAdapter implements RoomEventPublisherPort {
    private final RabbitTemplate rabbitTemplate;

    @Value("${routing.key.room.created}")
    private String routingKeyRoomCreated;

    @Value("${routing.key.room.update}")
    private String routingKeyRoomUpdate;

    @Value("${routing.key.room.deleted}")
    private String routingKeyRoomDeleted;

    @Value("${exchange.room.name}")
    private String exchangeRoomName;

    @Override
    public void createdRoom(Object payload) {
        rabbitTemplate.convertAndSend(exchangeRoomName, routingKeyRoomCreated, payload);
    }

    @Override
    public void updateRoom(Object payload) {
        rabbitTemplate.convertAndSend(exchangeRoomName, routingKeyRoomUpdate, payload);
    }

    @Override
    public void deletedRoom(Object payload) {
        rabbitTemplate.convertAndSend(exchangeRoomName, routingKeyRoomDeleted, payload);
    }
}
