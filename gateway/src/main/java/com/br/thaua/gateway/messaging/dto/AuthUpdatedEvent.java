package com.br.thaua.gateway.messaging.dto;

public record AuthUpdatedEvent(String eventType, Long id, String email, String oldEmail) {
}
