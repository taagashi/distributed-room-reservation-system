package com.br.thaua.room_service.core.services;

import com.br.thaua.room_service.domain.*;

import java.util.List;

public interface RoomServicePort {
    Room addNewRoom(Room room);
    Room likeRoom(Long authId, Long roomId);
    Equipment addNewEquipment(Equipment equipment);
    Room addEquipmentsInRoom(Long equipmentsId, Long roomId, Integer quantity);
    Room updateRoomById(Long roomId, Room room);
    Room fetchRoomById(Long roomId);
    FeedBack addFeedBackForRoom(FeedBack feedBack, Long roomId, Long authId, String authEmail);
    List<FeedBack> listFeedBackOfRoom(Long roomId, FeedBackType feedBackType, String author, Long authorId);
    void deleteRoomById(Long roomId);
    Equipment fetchEquipmentById(Long equipmentId);
    List<Room> listRoom(RoomStat stat, FeedBackType feedBackType, List<String> equipments);
    List<Equipment> listEquipments();
    FeedBack fetchFeedBackById(Long roomId, Long feedBackId);
    FeedBack updateFeedBackById(Long feedBackId, FeedBack feedBack, Long authId, String authEmail);
    void deleteFeedBackById(Long feedBackId, Long id);
    void updateRoomStat(Long roomId, RoomStat roomStat);
}