package com.br.thaua.auth_service.http.mappers;

import com.br.thaua.auth_service.domain.Auth;
import com.br.thaua.auth_service.domain.Role;
import com.br.thaua.auth_service.http.dto.AuthRequestLogin;
import com.br.thaua.auth_service.http.dto.AuthRequestSingIn;
import com.br.thaua.auth_service.http.dto.AuthResponse;
import com.br.thaua.auth_service.http.dto.AuthRootCreateAccountRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthDtoMappers {
    Auth map(AuthRequestLogin authRequestLogin);
    Auth map(AuthRequestSingIn authRequestSingIn);
    @Mapping(target = "roles", expression = "java(toDomainRoles(authRootCreateAccountRequest.roles()))")
    Auth map(AuthRootCreateAccountRequest authRootCreateAccountRequest);
    AuthResponse map(Auth auth);

    default List<Role> toDomainRoles(List<String> roles) {
        return roles.stream().map(Role::valueOf).toList();
    }
}
