package com.br.thaua.employee_service.messaging.dto;

import com.br.thaua.employee_service.domain.EventType;
import com.br.thaua.employee_service.domain.TypeParticipant;

public record ReservationAuthEventPresentNotPresent(
        EventType eventType,
        TypeParticipant typeParticipant,
        Long authId

) {
}
