package com.br.thaua.room_service.repositories.adapters;

import com.br.thaua.room_service.models.RoomEntity;
import com.br.thaua.room_service.repositories.RoomRepository;
import com.br.thaua.room_service.repositories.ports.RoomRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomRepositoryAdapter implements RoomRepositoryPort {
    private final RoomRepository roomRepository;

    @Override
    public RoomEntity save(RoomEntity roomEntity) {
        return roomRepository.save(roomEntity);
    }

    @Override
    public RoomEntity update(RoomEntity roomEntity) {
        return roomRepository.save(roomEntity);
    }

    @Override
    public RoomEntity findById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        roomRepository.deleteById(id);
    }
}
