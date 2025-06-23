package com.br.thaua.room_service.persistence.repository.adapters;

import com.br.thaua.room_service.domain.Room;
import com.br.thaua.room_service.persistence.mappers.RoomMapper;
import com.br.thaua.room_service.persistence.models.RoomEntity;
import com.br.thaua.room_service.persistence.repository.RoomRepository;
import com.br.thaua.room_service.core.repository.RoomRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomRepositoryAdapter implements RoomRepositoryPort {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Override
    public Room save(Room room) {
        RoomEntity saved = roomMapper.map(room);
        return roomMapper.map(roomRepository.save(saved));
    }

    @Override
    public Room update(Room room) {
        RoomEntity update = roomMapper.map(room);
        return roomMapper.map(roomRepository.save(update));
    }

    @Override
    public Room findById(Long id) {
        return roomMapper.map(roomRepository.findById(id).orElse(null));
    }

    @Override
    public void deleteById(Long id) {
        roomRepository.deleteById(id);
    }
}
