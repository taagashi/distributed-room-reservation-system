package com.br.thaua.reservation_service.restClient.mappers;

import com.br.thaua.reservation_service.domain.Auth;
import com.br.thaua.reservation_service.restClient.dto.AuthResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthRestClientMapper {
    Auth map(AuthResponse authResponse);
}
