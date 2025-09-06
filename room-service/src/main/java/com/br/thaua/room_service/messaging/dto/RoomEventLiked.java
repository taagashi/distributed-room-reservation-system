package com.br.thaua.room_service.messaging.dto;

import com.br.thaua.room_service.domain.EventType;

public record RoomEventLiked(EventType eventType, Long id, int roomNumber, Long authId) {
}
