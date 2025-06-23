package com.br.thaua.room_service.services.adapters;

import com.br.thaua.room_service.domain.Room;
import com.br.thaua.room_service.messaging.mappers.RoomEventMapper;
import com.br.thaua.room_service.core.messaging.RoomEventPublisherPort;
import com.br.thaua.room_service.core.repository.RoomRepositoryPort;
import com.br.thaua.room_service.core.services.RoomServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceAdapter implements RoomServicePort {
    private final RoomEventPublisherPort roomEventPublisherPort;
    private final RoomRepositoryPort roomRepositoryPort;
    private final RoomEventMapper roomEventMapper;

    @Override
    public Room addNewRoom(Room room) {
        Room saved = roomRepositoryPort.save(room);

        roomEventPublisherPort.createdRoom(roomEventMapper.map(saved));
        return saved;
    }

    @Override
    public Room updateRoomById(Long id, Room room) {
        Room roomUpdate = roomRepositoryPort.findById(id);

        roomUpdate.setCapacity(room.getCapacity());
        roomUpdate.setName(room.getName());

        Room updated = roomRepositoryPort.update(roomUpdate);

        roomEventPublisherPort.updateRoom(roomEventMapper.map(updated));
        return updated;
    }

    @Override
    public Room fetchRoomById(Long id) {
        return roomRepositoryPort.findById(id);
    }

    @Override
    public void deleteRoomById(Long id) {
        roomEventPublisherPort.deletedRoom(id);
        roomRepositoryPort.deleteById(id);
    }
}
