package com.br.thaua.room_service.persistence.mappers;

import com.br.thaua.room_service.domain.Room;
import com.br.thaua.room_service.persistence.models.RoomEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomEntity map(Room room);
    Room map(RoomEntity roomEntity);
}
