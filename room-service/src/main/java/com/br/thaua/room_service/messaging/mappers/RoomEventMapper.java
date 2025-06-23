package com.br.thaua.room_service.messaging.mappers;

import com.br.thaua.room_service.messaging.dto.RoomEvent;
import com.br.thaua.room_service.domain.Room;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomEventMapper {
    RoomEvent map(Room room);
}
