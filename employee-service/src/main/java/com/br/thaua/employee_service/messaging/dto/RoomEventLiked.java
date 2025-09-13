package com.br.thaua.employee_service.messaging.dto;

import com.br.thaua.employee_service.domain.EventType;
import com.br.thaua.employee_service.domain.RoomState;

public record RoomEventLiked(
        EventType eventType,
        Long roomId,
        Integer roomNumber,
        RoomState state,
        Long employeeId
) {
}
