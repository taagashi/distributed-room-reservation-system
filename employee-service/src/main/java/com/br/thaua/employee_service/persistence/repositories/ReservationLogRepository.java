package com.br.thaua.employee_service.persistence.repositories;

import com.br.thaua.employee_service.persistence.models.ReservationLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ReservationLogRepository extends JpaRepository<ReservationLogEntity, Long> {
    @Transactional
    @Modifying
    @Query("""
            UPDATE ReservationLogEntity r SET r.quantityReservations = r.quantityReservations + 1
            WHERE r.year = ?1
            AND r.moth = ?2
            """)
    int increaseReservation(Integer year, Integer moth);

    @Query("""
            SELECT CASE
            WHEN COUNT(r) > 0 THEN TRUE
            ELSE FALSE
            END
            FROM ReservationLogEntity r
            WHERE r.year = ?1
            AND r.moth = ?2
            """)
    boolean reservationExists(Integer year, Integer moth);

    @Query("""
            SELECT CASE
            WHEN COUNT(r) > 0 THEN TRUE
            ELSE FALSE
            END
            FROM ReservationLogEntity r
            WHERE r.year = ?1
            AND r.moth = ?2
            AND r.quantityReservations = 1
            """)
    boolean oneReservationExists(Integer year, Integer moth);

    @Transactional
    @Modifying
    @Query("""
            UPDATE ReservationLogEntity r SET r.quantityReservations = r.quantityReservations - 1
            WHERE r.year = ?1
            AND r.moth = ?2
            """)
    void deCreaseReservation(Integer year, Integer moth);

    void deleteByYearAndMoth(Integer year, Integer moth);

}
