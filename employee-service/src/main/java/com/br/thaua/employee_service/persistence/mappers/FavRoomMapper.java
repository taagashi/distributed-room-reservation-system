package com.br.thaua.employee_service.persistence.mappers;

import com.br.thaua.employee_service.domain.FavRoom;
import com.br.thaua.employee_service.persistence.models.FavRoomEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FavRoomMapper {
    FavRoom map(FavRoomEntity favRoomEntity);
    FavRoomEntity map(FavRoom favRoom);
}
