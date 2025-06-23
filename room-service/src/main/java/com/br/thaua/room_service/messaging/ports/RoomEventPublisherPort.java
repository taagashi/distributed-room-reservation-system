package com.br.thaua.room_service.messaging.ports;

public interface RoomEventPublisherPort {
    void createdRoom(Object payload);
    void updateRoom(Object payload);
    void deletedRoom(Object payload);
}
