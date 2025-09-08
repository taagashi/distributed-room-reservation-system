package com.br.thaua.room_service.http.dto;

import java.util.List;

public record GatewayRequest(Long id, String name, String email, List<String> roles) {
}
