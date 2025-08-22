package com.br.thaua.reservation_service.http.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationResponse(Long id, Long roomId, Integer roomNumber, LocalDate date, LocalTime start, LocalTime end, Integer participants) {}
