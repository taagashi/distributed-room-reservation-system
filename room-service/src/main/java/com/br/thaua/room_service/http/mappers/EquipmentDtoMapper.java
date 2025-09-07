package com.br.thaua.room_service.http.mappers;

import com.br.thaua.room_service.domain.Equipment;
import com.br.thaua.room_service.http.dto.EquipmentRequest;
import com.br.thaua.room_service.http.dto.EquipmentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipmentDtoMapper {
    Equipment map(EquipmentRequest equipmentRequest);
    EquipmentResponse map(Equipment equipment);
}
