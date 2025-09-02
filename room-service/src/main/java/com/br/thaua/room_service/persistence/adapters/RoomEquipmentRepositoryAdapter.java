package com.br.thaua.room_service.persistence.adapters;

import com.br.thaua.room_service.core.repository.RoomEquipmentRepositoryPort;
import com.br.thaua.room_service.domain.Room;
import com.br.thaua.room_service.persistence.mappers.EquipmentMapper;
import com.br.thaua.room_service.persistence.mappers.RoomMapper;
import com.br.thaua.room_service.persistence.models.EquipmentEntity;
import com.br.thaua.room_service.persistence.models.RoomEntity;
import com.br.thaua.room_service.persistence.models.RoomEquipmentEntity;
import com.br.thaua.room_service.persistence.repository.EquipmentRepository;
import com.br.thaua.room_service.persistence.repository.RoomEquipmentRepository;
import com.br.thaua.room_service.persistence.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoomEquipmentRepositoryAdapter implements RoomEquipmentRepositoryPort {
    private final RoomRepository roomRepository;
    private final EquipmentRepository equipmentRepository;
    private final RoomEquipmentRepository roomEquipmentRepository;
    private final RoomMapper roomMapper;

    @Override
    public Room updated(Long equipmentId, Long roomId, Integer quantity) {
        EquipmentEntity equipment = equipmentRepository.findById(equipmentId).get();

        RoomEntity roomEntity = roomRepository.findById(roomId).get();

        RoomEquipmentEntity roomEquipmentEntity =  roomEquipmentRepository.save(new RoomEquipmentEntity(roomEntity, equipment, quantity));
        return roomMapper.map(roomEquipmentEntity);
    }
}
