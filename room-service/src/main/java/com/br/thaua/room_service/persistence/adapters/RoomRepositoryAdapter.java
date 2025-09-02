package com.br.thaua.room_service.persistence.adapters;

import com.br.thaua.room_service.domain.FeedBackType;
import com.br.thaua.room_service.domain.Room;
import com.br.thaua.room_service.domain.RoomEquipment;
import com.br.thaua.room_service.domain.RoomStat;
import com.br.thaua.room_service.persistence.mappers.RoomEquipmentMapper;
import com.br.thaua.room_service.persistence.mappers.RoomMapper;
import com.br.thaua.room_service.persistence.models.RoomEntity;
import com.br.thaua.room_service.persistence.repository.RoomRepository;
import com.br.thaua.room_service.core.repository.RoomRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomRepositoryAdapter implements RoomRepositoryPort {
    private final RoomRepository roomRepository;
    private final RoomMapper mapper;

    @Override
    public Room save(Room room) {
        RoomEntity saved = mapper.map(room);
        return mapper.map(roomRepository.save(saved));
    }

    @Override
    public Room update(Room room) {
        RoomEntity update = mapper.map(room);
        return mapper.map(roomRepository.save(update));
    }

    @Override
    public Room findById(Long id) {
        return mapper.map(roomRepository.findById(id).orElse(null));
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll()
                .stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public List<Room> findAllByRoomStat(RoomStat roomStat) {
        return roomRepository.findAllByStat(roomStat)
                .stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public List<Room> findAllRoomEquipment(RoomStat roomStat, List<String> equipments, FeedBackType feedBackType) {
        return roomRepository.findAllRoomEquipment(roomStat, equipments, feedBackType)
                .stream()
                .map(mapper::map)
                .toList();

    }

    @Override
    public List<Room> findAllRoomEquipment(RoomStat roomStat, FeedBackType feedBackType) {
        return roomRepository.findAllRoomEquipment(roomStat, feedBackType)
                .stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public List<Room> findAllRoomEquipment(RoomStat roomStat, List<String> equipments) {
        return roomRepository.findAllRoomEquipment(roomStat, equipments)
                .stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public List<Room> findAllRoomEquipment(List<String> equipments, FeedBackType feedBackType) {
        return roomRepository.findAllRoomEquipment(equipments, feedBackType)
                .stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public List<Room> findAllRoomEquipment(List<String> equipments) {
        List<RoomEntity> roomEntities = roomRepository.findAllRoomEquipment(equipments, equipments.size());
        return roomEntities.stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public List<Room> findAllRoomEquipment(RoomStat roomStat) {
        return roomRepository.findAllRoomEquipment(roomStat)
                .stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public List<Room> findAllRoomEquipment(FeedBackType feedBackType) {
        return roomRepository.findAllRoomEquipment(feedBackType)
                .stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public void updateRoomStat(Long roomId, RoomStat roomStat) {
        roomRepository.updateRoomStat(roomId, roomStat);
    }
}
