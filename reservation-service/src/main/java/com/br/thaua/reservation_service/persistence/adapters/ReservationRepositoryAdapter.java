package com.br.thaua.reservation_service.persistence.adapters;

import com.br.thaua.reservation_service.core.repository.ReservationRepositoryPort;
import com.br.thaua.reservation_service.domain.Reservation;
import com.br.thaua.reservation_service.persistence.ReservationRepository;
import com.br.thaua.reservation_service.persistence.mappers.ReservationMapper;
import com.br.thaua.reservation_service.persistence.models.ReservationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public Reservation findById(Long id) {
        return reservationMapper.map(reservationRepository.findById(id).orElse(null));
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll().stream().map(reservationMapper::map).toList();
    }

    @Override
    public List<Reservation> findAllByEmployeeId(Long employeeId) {
        return reservationRepository.findAllByEmployeeId(employeeId).stream().map(reservationMapper::map).toList();
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public void deleteAllByEmployeeId(Long employeeId) {
        reservationRepository.deleteAllByEmployeeId(employeeId);
    }

    @Override
    public void deleteAllByRoomId(Long roomId) {
        reservationRepository.deleteAllByRoomId(roomId);
    }
}
