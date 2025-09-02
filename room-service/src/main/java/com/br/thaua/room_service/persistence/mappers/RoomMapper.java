package com.br.thaua.room_service.persistence.mappers;

import com.br.thaua.room_service.domain.Equipment;
import com.br.thaua.room_service.domain.Room;
import com.br.thaua.room_service.persistence.models.EquipmentEntity;
import com.br.thaua.room_service.persistence.models.RoomEntity;
import com.br.thaua.room_service.persistence.models.RoomEquipmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    @Mapping(target = "roomId", source = "id")
    @Mapping(target = "equipments", expression = "java(toDomain(roomEntity.getRoomEquipments()))")
    Room map(RoomEntity roomEntity);

    @Mapping(target = "id", source = "roomId")
    RoomEntity map(Room room);

    @Mapping(target = "roomId", source = "room.id")
    @Mapping(target = "roomNumber", source = "room.roomNumber")
    @Mapping(target = "capacity", source = "room.capacity")
    @Mapping(target = "stat", source = "room.stat")
    @Mapping(target = "equipments", expression = "java(toDomain(roomEquipmentEntity.getRoom().getRoomEquipments()))")
    @Mapping(target = "feedBacks", source = "room.feedBacks")
//    @Mapping(target = "equipments", expression = "java()")
//    @Mapping(target = "feedBacks", expression = "java(roomEquipmentEntity.getRoom().getFeedBacks())")
    Room map(RoomEquipmentEntity roomEquipmentEntity);
    
    default List<Equipment> toDomain(List<RoomEquipmentEntity> roomEquipments) {
        if(roomEquipments == null) {
            return null;
        }
        List<Equipment> equipments = new ArrayList<>();

        for (RoomEquipmentEntity roomEquipment : roomEquipments) {
            EquipmentEntity equipment = roomEquipment.getEquipment();
            Integer quantity = roomEquipment.getQuantity();
            equipments.add(new Equipment(equipment.getId(), equipment.getName(), quantity));
        }
        return equipments;
    }
}

