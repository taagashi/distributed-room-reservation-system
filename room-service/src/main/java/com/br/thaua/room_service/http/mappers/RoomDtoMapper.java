package com.br.thaua.room_service.http.mappers;

import com.br.thaua.room_service.domain.RoomEquipment;
import com.br.thaua.room_service.http.dto.RoomRequest;
import com.br.thaua.room_service.http.dto.RoomResponse;
import com.br.thaua.room_service.domain.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoomDtoMapper {
    Room map(RoomRequest roomRequest);

//    @Mapping(target = "feedBackResponses", source = "feedBacks")
//    @Mapping(target = "equipmentResponses", source = "equipments")
    RoomResponse map(Room room);
    RoomResponse map(RoomEquipment roomEquipment);
}
