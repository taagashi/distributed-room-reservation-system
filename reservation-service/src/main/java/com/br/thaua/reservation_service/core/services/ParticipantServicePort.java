package com.br.thaua.reservation_service.core.services;

import com.br.thaua.reservation_service.domain.Participant;
import com.br.thaua.reservation_service.domain.TypeParticipant;

import java.util.List;

public interface ParticipantServicePort {
    Participant fetchParticipantOfReservation(Long participantId, Long reservationId);
    List<Participant> addNewParticipantsForReservation(List<Participant> participants, Long reservationId, Long hostId);
    List<Participant> listParticipantsByReservationId(Long reservationId);
    List<Participant> listParticipantsOfOneTypeByReservationId(TypeParticipant typeParticipant, Long reservationId);
    List<Participant> listParticipantsWithSpecificCheackinByReservationId(boolean cheackin, Long reservationId);
}
