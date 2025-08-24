package com.br.thaua.reservation_service.persistence;

import com.br.thaua.reservation_service.persistence.models.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    @Query("""
            SELECT re FROM ReservationEntity re
            WHERE re.roomId = ?1
            AND re.date = ?2
            AND re.start <= ?3
            AND re.end >= ?4
            """)
    List<ReservationEntity> findAllByRoomIdAndDateAndStartLessThanEqualAndEndGreaterThanEqual(Long roomId, LocalDate date, LocalTime end, LocalTime start);
}
