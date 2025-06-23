package com.br.thaua.room_service.repositories.ports;

import com.br.thaua.room_service.models.RoomEntity;

public interface RoomRepositoryPort {
    RoomEntity save(RoomEntity roomEntity);
    RoomEntity update(RoomEntity roomEntity);
    RoomEntity findById(Long id);
    void deleteById(Long id);
}
