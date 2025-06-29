package com.br.thaua.auth_service.persistence.mappers;

import com.br.thaua.auth_service.domain.Auth;
import com.br.thaua.auth_service.domain.Role;
import com.br.thaua.auth_service.persistence.models.AuthEntity;
import com.br.thaua.auth_service.persistence.models.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    @Mapping(target = "roles", expression = "java(toEntityRole(auth.getRoles()))")
    AuthEntity map(Auth auth);

    @Mapping(target = "roles", expression = "java(toDomainRole(authEntity.getRoles()))")
    Auth map(AuthEntity authEntity);

    default List<RoleEntity> toEntityRole(List<Role> roleList) {
        if(roleList == null) {
            return null;
        }
        return roleList.stream().map(role -> new RoleEntity(role.getRole(), null)).toList();
    }

    default List<Role> toDomainRole(List<RoleEntity> roleEntityList) {
        if(roleEntityList == null) {
            return null;
        }
        return roleEntityList.stream()
                .map(role -> Role.valueOf(role.getRole()))
                .toList();
    }

}
