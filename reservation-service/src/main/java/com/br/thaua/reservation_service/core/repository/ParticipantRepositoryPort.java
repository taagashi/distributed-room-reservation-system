package com.br.thaua.reservation_service.core.repository;

import com.br.thaua.reservation_service.domain.Participant;
import com.br.thaua.reservation_service.domain.Reservation;
import com.br.thaua.reservation_service.domain.TypeParticipant;
import com.br.thaua.reservation_service.persistence.models.ParticipantEntity;
import com.br.thaua.reservation_service.persistence.models.TypeParticipantEntity;

import java.util.List;

public interface ParticipantRepositoryPort {
    Participant save(Participant participant);
    List<Participant> saveAll(List<Participant> participants);
    Participant findByAuthIdAndTypeParticipantAndReservation(Long authId, TypeParticipant typeParticipant, Reservation reservation);
    Participant findByAuthIdAndReservationId(Long participantId, Long reservationId);
    Participant findByAuthIdAndTypeParticipantAndReservationId(Long authId, TypeParticipant typeParticipant, Long reservationId);
    List<Participant> findAllByReservation(Reservation reservation);
    List<Participant> findAllByTypeParticipantAndReservationId(TypeParticipant typeParticipant, Long reservationId);
    List<Participant> findAllByCheackinAndReservationId(boolean cheackin, Long reservationId);
    List<Reservation> findAllReservationByAuthId(Long authId);
    void deleteAllById(List<Long> participantsIds);
    void deleteAllByAuthId(Long id);
    void updateEmailByAuthId(Long id, String email);
    List<Participant> findAllByReservationId(Long reservationId);
}
