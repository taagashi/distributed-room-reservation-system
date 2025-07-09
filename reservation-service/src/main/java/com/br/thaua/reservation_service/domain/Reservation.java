package com.br.thaua.reservation_service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    private Long id;
    private Long roomId;
    private Integer roomNumber;
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;
    private Integer participants;
}
