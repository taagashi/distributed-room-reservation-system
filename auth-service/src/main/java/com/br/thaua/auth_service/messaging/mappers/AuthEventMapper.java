package com.br.thaua.auth_service.messaging.mappers;

import com.br.thaua.auth_service.domain.Auth;
import com.br.thaua.auth_service.domain.Role;
import com.br.thaua.auth_service.messaging.dto.AuthEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthEventMapper {
    @Mapping(target = "roles", expression = "java(toEventRole(auth.getRoles()))")
    AuthEvent map(Auth auth);

    default List<String> toEventRole(List<Role> roles) {
        if(roles == null) {
            return  null;
        }
        return roles.stream().map(Enum::name).toList();
    }
}
