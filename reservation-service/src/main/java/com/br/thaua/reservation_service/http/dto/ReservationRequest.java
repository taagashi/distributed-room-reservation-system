package com.br.thaua.reservation_service.http.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationRequest(Long roomId, LocalDate date, LocalTime start, LocalTime end) {}
