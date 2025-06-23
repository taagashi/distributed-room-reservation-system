package com.br.thaua.room_service.services.ports;

import com.br.thaua.room_service.controllers.dto.RoomRequest;
import com.br.thaua.room_service.controllers.dto.RoomResponse;

public interface RoomServicePort {
    RoomResponse addNewRoom(RoomRequest roomRequest);
    RoomResponse updateRoomById(Long id, RoomRequest roomRequest);
    RoomResponse fetchRoomById(Long id);
    void deleteRoomById(Long id);
}