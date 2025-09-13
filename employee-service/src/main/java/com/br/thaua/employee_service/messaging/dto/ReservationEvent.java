package com.br.thaua.employee_service.messaging.dto;

import com.br.thaua.employee_service.domain.EventType;

public record ReservationEvent(
        EventType eventType,
        Integer year,
        Integer moth,
        Long authId
) {
}
