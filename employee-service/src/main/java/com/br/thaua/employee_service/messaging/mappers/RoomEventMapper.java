package com.br.thaua.employee_service.messaging.mappers;

import com.br.thaua.employee_service.domain.FavRoom;
import com.br.thaua.employee_service.messaging.dto.RoomEventLiked;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoomEventMapper {
    @Mapping(target = "id", source = "roomId")
    @Mapping(target = "employee.id", source = "employeeId")
    FavRoom map(RoomEventLiked roomEventLiked);
}
