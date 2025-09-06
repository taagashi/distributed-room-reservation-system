package com.br.thaua.room_service.messaging.dto;

import com.br.thaua.room_service.domain.EventType;

public record RoomEventFeedBack(
        EventType eventType,
        Long authId
) {
}
