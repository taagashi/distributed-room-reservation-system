package com.br.thaua.reservation_service.core.services;

import com.br.thaua.reservation_service.domain.Reservation;

import java.util.List;

public interface ReservationServicePort {
    Reservation addNewReservation(Reservation reservation, Long authId, String email);
    Reservation updateReservation(Long reservationId, Long hostId, Reservation reservation);
    Reservation fetchReservationById(Long id);
    List<Reservation> fetchAllReservation();
    List<Reservation> fetchAllReservationByEmployee(Long authId);
    void deleteReservationById(Long reservationId, Long hostId);
}
