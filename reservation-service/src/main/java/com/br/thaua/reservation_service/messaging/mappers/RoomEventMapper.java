package com.br.thaua.reservation_service.messaging.mappers;

import com.br.thaua.reservation_service.domain.Room;
import com.br.thaua.reservation_service.messaging.dto.consumer.RoomEventConsumer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomEventMapper {
    Room map(RoomEventConsumer roomEventConsumer);
}
