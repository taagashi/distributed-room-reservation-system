package com.br.thaua.room_service.http.mappers;

import com.br.thaua.room_service.http.dto.RoomRequest;
import com.br.thaua.room_service.http.dto.RoomResponse;
import com.br.thaua.room_service.domain.Room;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomDtoMapper {
    Room map(RoomRequest roomRequest);
    RoomResponse map(Room room);
}
