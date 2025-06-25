package com.br.thaua.reservation_service.core.services;

import com.br.thaua.reservation_service.domain.Reservation;

import java.util.List;

public interface ReservationServicePort {
    Reservation addNewReservation(Reservation reservation);
    Reservation updateReservation(Long id, Reservation reservation);
    Reservation fetchReservationById(Long id);
    List<Reservation> fetchAllReservation();
    List<Reservation> fetchAllReservationByEmployee(Long employeeId);
    void deleteReservationById(Long id);
}
