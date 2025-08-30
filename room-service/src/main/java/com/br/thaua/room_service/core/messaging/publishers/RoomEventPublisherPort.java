package com.br.thaua.room_service.core.messaging.publishers;

public interface RoomEventPublisherPort {
    void sendToRoomExchange(Object payload);
}
