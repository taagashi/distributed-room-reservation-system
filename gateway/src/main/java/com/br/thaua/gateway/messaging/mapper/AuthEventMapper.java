package com.br.thaua.gateway.messaging.mapper;

import com.br.thaua.gateway.domain.Auth;
import com.br.thaua.gateway.messaging.dto.AuthEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthEventMapper {
    AuthEvent map(Auth auth);
}
