package com.br.thaua.auth_service.messaging.mappers;

import com.br.thaua.auth_service.domain.Auth;
import com.br.thaua.auth_service.messaging.dto.AuthEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthEventMapper {
    AuthEvent map(Auth auth);
}
