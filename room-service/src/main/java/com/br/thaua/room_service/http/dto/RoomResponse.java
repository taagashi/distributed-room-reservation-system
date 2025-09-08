package com.br.thaua.room_service.http.dto;

import java.util.List;

public record RoomResponse(Long roomId,
                           Integer roomNumber,
                           Integer capacity,
                           String stat,
                           List<EquipmentResponse> equipments,
                           List<FeedBackResponse> feedBacks
) {}
