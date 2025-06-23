package com.br.thaua.room_service.mappers;

import com.br.thaua.room_service.controllers.dto.RoomRequest;
import com.br.thaua.room_service.controllers.dto.RoomResponse;
import com.br.thaua.room_service.models.RoomEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomEntity map(RoomRequest roomRequest);
    RoomResponse map(RoomEntity roomEntity);
}
