package com.br.thaua.reservation_service.http.dto;

import java.time.LocalDateTime;

public record ReservationResponse(Long id, Long employeeId, Long roomId, LocalDateTime reservedAt) {}
