package com.br.thaua.room_service.persistence.mappers;

import com.br.thaua.room_service.domain.Equipment;
import com.br.thaua.room_service.domain.RoomEquipment;
import com.br.thaua.room_service.persistence.models.EquipmentEntity;
import com.br.thaua.room_service.persistence.models.RoomEntity;
import com.br.thaua.room_service.persistence.models.RoomEquipmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomEquipmentMapper {
    @Mapping(target = "roomId", source = "id")
    @Mapping(target = "equipments", expression = "java(toDomain(roomEntity.getRoomEquipments()))")
    RoomEquipment map(RoomEntity roomEntity);

    default List<Equipment> toDomain(List<RoomEquipmentEntity> roomEquipmentEntities) {
        if(roomEquipmentEntities.isEmpty()) {
            return null;
        }
        List<Equipment> equipments = new ArrayList<>();

        for(int i=0 ; i<=roomEquipmentEntities.size() ; i++) {
            EquipmentEntity equipment = roomEquipmentEntities.get(i).getEquipment();
            Integer quantity = roomEquipmentEntities.get(i).getQuantity();
            equipments.add(new Equipment(equipment.getId(), equipment.getName(), quantity));
        }
        return equipments;
    }
}
