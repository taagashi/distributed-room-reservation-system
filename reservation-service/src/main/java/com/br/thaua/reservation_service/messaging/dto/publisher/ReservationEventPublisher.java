package com.br.thaua.reservation_service.messaging.dto.publisher;

import com.br.thaua.reservation_service.domain.EventType;

public record ReservationEventPublisher(EventType eventType, int year, int month, Long employeeId) {
}
