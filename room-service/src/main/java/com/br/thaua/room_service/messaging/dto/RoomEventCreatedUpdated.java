package com.br.thaua.room_service.messaging.dto;

import com.br.thaua.room_service.domain.EventType;

public record RoomEventCreatedUpdated(EventType eventType, Long id, int roomNumber, int capacity) {}
