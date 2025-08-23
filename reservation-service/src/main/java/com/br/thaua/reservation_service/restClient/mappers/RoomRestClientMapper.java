package com.br.thaua.reservation_service.restClient.mappers;

import com.br.thaua.reservation_service.domain.Room;
import com.br.thaua.reservation_service.restClient.dto.RoomResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomRestClientMapper {
    Room map(RoomResponse roomResponse);
}
