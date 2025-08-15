package com.br.thaua.reservation_service.core.repository;

import com.br.thaua.reservation_service.domain.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservationRepositoryPort {
    Reservation save(Reservation reservation);
    List<Reservation> findAll();
    List<Reservation> findAllByDate(LocalDate date);
    List<Reservation> findAllByParticipantEntityEmployeeId(Long employeeId);
    void deleteById(Long id);
    Reservation findById(Long reservationId);
    void deleteAllByRoomId(Long id);
    void delete(Reservation reservation);
    void updateRoomNumberByRoomId(Long id, Integer roomNumber);
    List<Reservation> findAllByRoomIdAndDateAndStartLessThanEqualAndEndGreaterThanEqual(Long roomId, LocalDate date, LocalTime end, LocalTime start);
}
