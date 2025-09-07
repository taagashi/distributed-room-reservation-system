package com.br.thaua.room_service.http.mappers;

import com.br.thaua.room_service.domain.Equipment;
import com.br.thaua.room_service.http.dto.EquipmentResponseSimple;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipmentDtoSimpleMapper {
    EquipmentResponseSimple map(Equipment equipment);
}
