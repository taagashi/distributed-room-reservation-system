package com.br.thaua.gateway.messaging.dto;

public record AuthUpdatedEvent(String eventType, Long authId, String email, String oldEmail) {
}
