package com.br.thaua.reservation_service.messaging.mappers;

import com.br.thaua.reservation_service.domain.Auth;
import com.br.thaua.reservation_service.messaging.dto.consumer.AuthEventConsumer;
import com.br.thaua.reservation_service.messaging.dto.consumer.AuthUpdatedEventConsumer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthEventMapper {
    @Mapping(target = "id", source = "authId")
    Auth map(AuthEventConsumer authEventConsumer);

    @Mapping(target = "id", source = "authId")
    Auth map(AuthUpdatedEventConsumer authUpdatedEventConsumer);
}
