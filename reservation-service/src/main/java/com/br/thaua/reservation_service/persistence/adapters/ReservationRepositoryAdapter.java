package com.br.thaua.reservation_service.persistence.adapters;

import com.br.thaua.reservation_service.core.repository.ReservationRepositoryPort;
import com.br.thaua.reservation_service.domain.Reservation;
import com.br.thaua.reservation_service.persistence.ReservationRepository;
import com.br.thaua.reservation_service.persistence.mappers.ReservationMapper;
import com.br.thaua.reservation_service.persistence.models.ReservationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationRepositoryAdapter implements ReservationRepositoryPort {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    @Override
    public Reservation save(Reservation reservation) {
        ReservationEntity saved = reservationMapper.map(reservation);
        return reservationMapper.map(reservationRepository.save(saved));
    }

    @Override
    public List<Reservation> findAll() {
        return List.of();
    }

    @Override
    public List<Reservation> findAllByDate(LocalDate date) {
        return List.of();
    }

    @Override
    public List<Reservation> findAllByParticipantEntityEmployeeId(Long employeeId) {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public Reservation findById(Long reservationId) {
        return reservationMapper.map(reservationRepository.findById(reservationId).orElse(null));
    }

    @Override
    public void deleteAllByRoomId(Long id) {

    }

    @Override
    public void delete(Reservation reservation) {
        reservationRepository.delete(reservationMapper.map(reservation));
    }

    @Override
    public void updateRoomNumberByRoomId(Long id, Integer roomNumber) {

    }

    @Override
    public List<Reservation> findAllByRoomIdAndDateAndStartLessThanEqualAndEndGreaterThanEqual(Long roomId, LocalDate date, LocalTime end, LocalTime start) {
        return reservationRepository.findAllByRoomIdAndDateAndStartLessThanEqualAndEndGreaterThanEqual(
                roomId, date, end, start).stream().map(reservationMapper::map).toList();
    }
}
