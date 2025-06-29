package com.br.thaua.auth_service.security.mappers;

import com.br.thaua.auth_service.domain.Auth;
import com.br.thaua.auth_service.security.details.AuthDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthSecurityMapper {
    Auth map(AuthDetails authDetails);
}
