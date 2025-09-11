package com.br.thaua.employee_service.core.repository;

import com.br.thaua.employee_service.domain.ReservationLog;

public interface ReservationLogRepositoryPort {
    void save(ReservationLog reservationLog);
    void deleteReservation(Integer year, Integer moth);
    boolean reservationExists(Integer year, Integer moth);
    boolean oneReservationExists(Integer year, Integer moth);
    void increaseReservation(Integer year, Integer moth);
    void deCreaseReservation(Integer year, Integer moth);
    void updateReservation(ReservationLog reservationLog);
}
