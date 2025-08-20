package com.br.thaua.reservation_service.persistence;

import com.br.thaua.reservation_service.domain.Participant;
import com.br.thaua.reservation_service.domain.Reservation;
import com.br.thaua.reservation_service.persistence.models.ParticipantEntity;
import com.br.thaua.reservation_service.persistence.models.ReservationEntity;
import com.br.thaua.reservation_service.persistence.models.TypeParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {
    ParticipantEntity findByAuthIdAndTypeParticipantAndReservation(Long authId, TypeParticipantEntity typeParticipant, ReservationEntity reservationEntity);
    ParticipantEntity findByAuthIdAndTypeParticipantAndReservationId(Long authId, TypeParticipantEntity typeParticipant, Long reservationId);
    List<ReservationEntity> findAllReservationByAuthId(Long authId);
    List<ParticipantEntity> findAllByReservation(ReservationEntity reservationEntity);
    List<ParticipantEntity> findAllByTypeParticipantAndReservationId(TypeParticipantEntity map, Long reservationId);
    List<ParticipantEntity> findAllByCheackinAndReservationId(boolean cheackin, Long reservationId);
    List<ParticipantEntity> findAllByReservationId(Long reservationId);
    ParticipantEntity findByAuthIdAndReservationId(Long authId, Long reservationId);
}
