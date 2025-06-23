package com.br.thaua.room_service.core.repository;

import com.br.thaua.room_service.domain.Room;

public interface RoomRepositoryPort {
    Room save(Room room);
    Room update(Room room);
    Room findById(Long id);
    void deleteById(Long id);
}
