package com.br.thaua.employee_service.persistence.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reservationLog_tb")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationLogEntity {
    @Column(name = "year_reservation")
    private Integer year;

    @Id
    @Column(unique = true, name = "moth_reservation")
    private Integer moth;
    private Long quantityReservations = 0L;

    @ManyToOne
    private EmployeeEntity employee;


}
