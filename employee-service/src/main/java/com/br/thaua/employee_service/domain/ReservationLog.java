package com.br.thaua.employee_service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReservationLog {
    private Long id;
    private Integer year;
    private Integer moth;
    private Long QuantityReservations;
    private Employee employee;
}
