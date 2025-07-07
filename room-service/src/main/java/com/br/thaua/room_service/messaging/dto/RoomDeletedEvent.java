package com.br.thaua.room_service.messaging.dto;

public record RoomDeletedEvent(String eventType, Long roomId) {
}
