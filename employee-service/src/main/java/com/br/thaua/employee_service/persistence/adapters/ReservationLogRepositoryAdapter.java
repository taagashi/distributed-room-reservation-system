package com.br.thaua.employee_service.persistence.adapters;

import com.br.thaua.employee_service.core.repository.ReservationLogRepositoryPort;
import com.br.thaua.employee_service.domain.ReservationLog;
import com.br.thaua.employee_service.persistence.mappers.ReservationLogMapper;
import com.br.thaua.employee_service.persistence.models.ReservationLogEntity;
import com.br.thaua.employee_service.persistence.repositories.ReservationLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationLogRepositoryAdapter implements ReservationLogRepositoryPort {
    private final ReservationLogRepository reservationLogRepository;
    private final ReservationLogMapper mapper;

    @Override
    public void save(ReservationLog reservationLog) {
        ReservationLogEntity saved = mapper.map(reservationLog);
        reservationLogRepository.save(saved);
    }

    @Override
    public void deleteReservation(Integer year, Integer moth) {
        reservationLogRepository.deleteByYearAndMoth(year, moth);
    }

    @Override
    public boolean reservationExists(Integer year, Integer moth) {
        return reservationLogRepository.reservationExists(year, moth);
    }

    @Override
    public boolean oneReservationExists(Integer year, Integer moth) {
        return reservationLogRepository.oneReservationExists(year, moth);
    }

    @Override
    public void increaseReservation(Integer year, Integer moth) {
        reservationLogRepository.increaseReservation(year, moth);
    }

    @Override
    public void deCreaseReservation(Integer year, Integer moth) {
        reservationLogRepository.deCreaseReservation(year, moth);
    }

    @Override
    public void updateReservation(ReservationLog reservationLog) {
        ReservationLogEntity updated = mapper.map(reservationLog);
        reservationLogRepository.save(updated);
    }
}
