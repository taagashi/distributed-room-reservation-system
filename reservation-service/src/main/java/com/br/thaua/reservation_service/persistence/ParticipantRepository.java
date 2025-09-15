package com.br.thaua.reservation_service.persistence;

import com.br.thaua.reservation_service.domain.TypeParticipant;
import com.br.thaua.reservation_service.persistence.models.ParticipantEntity;
import com.br.thaua.reservation_service.persistence.models.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {
    @Query("""
            SELECT p FROM ParticipantEntity p
            WHERE p.authId = ?1
            AND p.typeParticipant = ?2
            AND p.reservation = ?3
            """)
    ParticipantEntity findByAuthIdAndTypeParticipantAndReservation(Long authId, TypeParticipant typeParticipant, ReservationEntity reservationEntity);

    @Query("""
            SELECT p FROM ParticipantEntity p
            WHERE p.authId = ?1
            AND p.typeParticipant = ?2
            AND p.reservation.id = ?3
            """)
    ParticipantEntity findByAuthIdAndTypeParticipantAndReservationId(Long authId, TypeParticipant typeParticipant, Long reservationId);

    @Query("""
            SELECT p FROM ParticipantEntity p
            WHERE p.authId = ?1
            """)
    List<ReservationEntity> findAllReservationByAuthId(Long authId);

    @Query("""
            SELECT p FROM ParticipantEntity p
            WHERE p.reservation = ?1
            """)
    List<ParticipantEntity> findAllByReservation(ReservationEntity reservationEntity);

    @Query("""
            SELECT p FROM ParticipantEntity p
            WHERE p.typeParticipant = ?1
            AND p.reservation.id = ?2
            """)
    List<ParticipantEntity> findAllByTypeParticipantAndReservationId(TypeParticipant typeParticipant, Long reservationId);

    @Query("""
            SELECT p FROM ParticipantEntity p
            WHERE p.cheackin = ?1
            AND p.reservation.id = ?2
            """)
    List<ParticipantEntity> findAllByCheackinAndReservationId(boolean cheackin, Long reservationId);

    @Query("""
            SELECT p FROM ParticipantEntity p
            WHERE p.reservation.id = ?1
            """)
    List<ParticipantEntity> findAllByReservationId(Long reservationId);

    @Query("""
            SELECT p FROM ParticipantEntity p
            WHERE p.authId = ?1
            AND p.reservation.id = ?2
            """)
    ParticipantEntity findByAuthIdAndReservationId(Long authId, Long reservationId);
}
