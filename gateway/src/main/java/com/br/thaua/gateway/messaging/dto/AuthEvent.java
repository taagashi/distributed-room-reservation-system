package com.br.thaua.gateway.messaging.dto;

public record AuthEvent(String eventType, Long id, String email) {
}
