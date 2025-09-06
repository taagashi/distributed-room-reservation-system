package com.br.thaua.room_service.messaging.dto;

import com.br.thaua.room_service.domain.EventType;

public record RoomLiked(EventType eventType, Long id, int number, Long authId) {
}
