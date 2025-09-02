package com.br.thaua.room_service.persistence.mappers;

import com.br.thaua.room_service.domain.Equipment;
import com.br.thaua.room_service.persistence.models.EquipmentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipmentMapper {
    Equipment map(EquipmentEntity equipmentEntity);
    EquipmentEntity map(Equipment equipment);
}
