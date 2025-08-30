package com.br.thaua.room_service.core.repository;

import com.br.thaua.room_service.domain.FeedBackType;
import com.br.thaua.room_service.domain.Room;
import com.br.thaua.room_service.domain.RoomEquipment;
import com.br.thaua.room_service.domain.RoomStat;
import com.br.thaua.room_service.persistence.models.RoomEquipmentEntity;

import java.util.List;

public interface RoomRepositoryPort {
    Room save(Room room);
    Room update(Room room);
    Room findById(Long id);
    List<Room> findAll();
    void deleteById(Long id);
    List<Room> findAllByRoomStat(RoomStat roomStat);
    List<Room> findAllRoomEquipment(RoomStat roomStat, List<String> equipments, FeedBackType feedBackType);
    List<Room> findAllRoomEquipment(RoomStat roomStat, FeedBackType feedBackType);
    List<Room> findAllRoomEquipment(RoomStat roomStat, List<String> equipments);
    List<Room> findAllRoomEquipment(List<String> equipments, FeedBackType feedBackType);
    List<Room> findAllRoomEquipment(List<String> equipments);
    List<Room> findAllRoomEquipment(RoomStat roomStat);
    List<Room> findAllRoomEquipment(FeedBackType feedBackType);
    void updateRoomStat(Long roomId, RoomStat roomStat);
}
