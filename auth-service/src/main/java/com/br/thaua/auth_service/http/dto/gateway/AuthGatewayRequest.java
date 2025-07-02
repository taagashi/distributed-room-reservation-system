package com.br.thaua.auth_service.http.dto.gateway;

import java.util.List;

public record AuthGatewayRequest(Long id, String name, String email, List<String> roles) {
}
