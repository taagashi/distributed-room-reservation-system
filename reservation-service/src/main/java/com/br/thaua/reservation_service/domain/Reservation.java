package com.br.thaua.reservation_service.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Reservation {
    private Long id;
    private Long employeeId;
    private Long roomId;
    private LocalDateTime reservedAt;

}
