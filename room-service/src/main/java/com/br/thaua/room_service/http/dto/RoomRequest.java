package com.br.thaua.room_service.http.dto;

public record RoomRequest(
        int roomNumber,
        int capacity,
        String stat
) {}
