package com.br.thaua.auth_service.messaging.dto;

import com.br.thaua.auth_service.domain.EventType;

public record AuthUpdatedEvent(EventType eventType, Long id, String email, String oldEmail) {
}
