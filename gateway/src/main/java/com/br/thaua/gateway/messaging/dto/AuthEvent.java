package com.br.thaua.gateway.messaging.dto;

public record AuthEvent(String eventType, Long authId, String email) {
}
