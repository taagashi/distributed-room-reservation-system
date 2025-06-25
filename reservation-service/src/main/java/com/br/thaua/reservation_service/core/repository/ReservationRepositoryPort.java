package com.br.thaua.reservation_service.core.repository;

import com.br.thaua.reservation_service.domain.Reservation;

import java.util.List;

public interface ReservationRepositoryPort {
    Reservation save(Reservation reservation);
    Reservation findById(Long id);
    List<Reservation> findAll();
    List<Reservation> findAllByEmployeeId(Long employeeId);
    void deleteById(Long id);
    void deleteAllByEmployeeId(Long employeeId);
    void deleteAllByRoomId(Long roomId);
}
