package com.br.thaua.employee_service.messaging.dto;

import com.br.thaua.employee_service.domain.EventType;

public record AuthEvent(
        EventType eventType,
        Long authId,
        String email
) {
}
