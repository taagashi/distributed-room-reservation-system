package com.br.thaua.room_service.services.adapters;

import com.br.thaua.room_service.controllers.dto.RoomRequest;
import com.br.thaua.room_service.controllers.dto.RoomResponse;
import com.br.thaua.room_service.mappers.RoomEventMapper;
import com.br.thaua.room_service.mappers.RoomMapper;
import com.br.thaua.room_service.messaging.ports.RoomEventPublisherPort;
import com.br.thaua.room_service.models.RoomEntity;
import com.br.thaua.room_service.repositories.ports.RoomRepositoryPort;
import com.br.thaua.room_service.services.ports.RoomServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceAdapter implements RoomServicePort {
    private final RoomEventPublisherPort roomEventPublisherPort;
    private final RoomRepositoryPort roomRepositoryPort;
    private final RoomEventMapper roomEventMapper;
    private final RoomMapper roomMapper;

    @Override
    public RoomResponse addNewRoom(RoomRequest roomRequest) {
        RoomEntity roomEntity = roomMapper.map(roomRequest);
        RoomEntity saved = roomRepositoryPort.save(roomEntity);

        roomEventPublisherPort.createdRoom(roomEventMapper.map(saved));
        return roomMapper.map(saved);
    }

    @Override
    public RoomResponse updateRoomById(Long id, RoomRequest roomRequest) {
        RoomEntity roomEntityUpdate = roomRepositoryPort.findById(id);

        roomEntityUpdate.setCapacity(roomRequest.capacity());
        roomEntityUpdate.setName(roomRequest.name());

        RoomEntity updated = roomRepositoryPort.update(roomEntityUpdate);

        roomEventPublisherPort.updateRoom(roomEventMapper.map(updated));
        return roomMapper.map(updated);
    }

    @Override
    public RoomResponse fetchRoomById(Long id) {
        return roomMapper.map(roomRepositoryPort.findById(id));
    }

    @Override
    public void deleteRoomById(Long id) {
        roomEventPublisherPort.deletedRoom(id);
        roomRepositoryPort.deleteById(id);
    }
}
