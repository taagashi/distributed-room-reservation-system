package com.br.thaua.employee_service.messaging.mappers;

import com.br.thaua.employee_service.domain.Employee;
import com.br.thaua.employee_service.messaging.dto.AuthEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthEventMapper {
    @Mapping(target = "id", source = "authId")
    Employee map(AuthEvent authEvent);
}
