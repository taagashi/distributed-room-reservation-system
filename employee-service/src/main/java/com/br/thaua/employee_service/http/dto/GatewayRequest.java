package com.br.thaua.employee_service.http.dto;

import java.util.List;

public record GatewayRequest(
        Long id,
        String name,
        String email,
        List<String> roles
) {
}
