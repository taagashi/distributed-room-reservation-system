package com.br.thaua.gateway.messaging.mapper;

import com.br.thaua.gateway.domain.Auth;
import com.br.thaua.gateway.messaging.dto.AuthEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthEventMapper {
    @Mapping(target = "authId", source = "id")
    AuthEvent map(Auth auth);
}
