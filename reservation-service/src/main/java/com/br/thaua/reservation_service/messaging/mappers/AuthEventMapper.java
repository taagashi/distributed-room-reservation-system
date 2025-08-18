package com.br.thaua.reservation_service.messaging.mappers;

import com.br.thaua.reservation_service.domain.Auth;
import com.br.thaua.reservation_service.messaging.dto.consumer.AuthEventConsumer;
import com.br.thaua.reservation_service.messaging.dto.consumer.AuthUpdatedEventConsumer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthEventMapper {
    Auth map(AuthEventConsumer authEventConsumer);
    Auth map(AuthUpdatedEventConsumer authUpdatedEventConsumer);
}
