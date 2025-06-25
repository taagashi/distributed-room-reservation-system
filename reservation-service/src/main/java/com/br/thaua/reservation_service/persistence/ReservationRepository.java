package com.br.thaua.reservation_service.persistence;

import com.br.thaua.reservation_service.domain.Reservation;
import com.br.thaua.reservation_service.persistence.models.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    List<ReservationEntity> findAllByEmployeeId(Long employeeId);
    void deleteAllByEmployeeId(Long employeeId);
    void deleteAllByRoomId(Long roomId);
}
