package com.br.thaua.room_service.core.services;

import com.br.thaua.room_service.domain.Room;

public interface RoomServicePort {
    Room addNewRoom(Room room);
    Room updateRoomById(Long id, Room room);
    Room fetchRoomById(Long id);
    void deleteRoomById(Long id);
}