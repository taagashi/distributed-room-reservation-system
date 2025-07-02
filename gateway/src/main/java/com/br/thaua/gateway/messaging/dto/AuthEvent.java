package com.br.thaua.gateway.messaging.dto;

import java.util.List;

public record AuthEvent(Long id, String name, String email, List<String> roles) {
}
