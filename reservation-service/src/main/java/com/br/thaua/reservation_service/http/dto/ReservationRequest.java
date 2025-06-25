package com.br.thaua.reservation_service.http.dto;

import java.time.LocalDateTime;

public record ReservationRequest(Long employeeId, Long roomId, LocalDateTime reservedAt) {}
