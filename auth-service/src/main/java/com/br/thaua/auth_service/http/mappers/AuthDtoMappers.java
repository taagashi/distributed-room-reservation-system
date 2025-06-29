package com.br.thaua.auth_service.http.mappers;

import com.br.thaua.auth_service.domain.Auth;
import com.br.thaua.auth_service.http.dto.AuthRequestLogin;
import com.br.thaua.auth_service.http.dto.AuthRequestSingIn;
import com.br.thaua.auth_service.http.dto.AuthResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthDtoMappers {
    Auth map(AuthRequestLogin authRequestLogin);
    Auth map(AuthRequestSingIn authRequestSingIn);
    AuthResponse map(Auth auth);
}
