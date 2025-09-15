package com.br.thaua.auth_service.messaging.mappers;

import com.br.thaua.auth_service.domain.Auth;
import com.br.thaua.auth_service.domain.EventType;
import com.br.thaua.auth_service.messaging.dto.AuthEvent;
import com.br.thaua.auth_service.messaging.dto.AuthUpdatedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthEventMapper {
    @Mapping(target = "authId", source = "auth.id")
    @Mapping(target = "eventType", source = "eventType")
    AuthEvent map(Auth auth, EventType eventType);

    @Mapping(target = "authId", source = "auth.id")
    @Mapping(target = "eventType", source = "eventType")
    @Mapping(target = "oldEmail", source = "oldEmail")
    AuthUpdatedEvent map(Auth auth, String oldEmail, EventType eventType);
}
