package com.br.thaua.room_service.core.repository;

import com.br.thaua.room_service.domain.Room;

public interface RoomEquipmentRepositoryPort {
    Room updated(Long equipmentId, Long roomId, Integer quantity);
}
