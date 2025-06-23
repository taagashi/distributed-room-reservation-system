package com.br.thaua.room_service.mappers;

import com.br.thaua.room_service.controllers.dto.RoomEvent;
import com.br.thaua.room_service.models.RoomEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomEventMapper {
    RoomEvent map(RoomEntity roomEntity);
}
