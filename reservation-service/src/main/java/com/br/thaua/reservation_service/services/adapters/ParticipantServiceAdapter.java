package com.br.thaua.reservation_service.services.adapters;

import com.br.thaua.reservation_service.core.cache.AuthCachePort;
import com.br.thaua.reservation_service.core.cache.RoomCachePort;
import com.br.thaua.reservation_service.core.cache.validators.AuthCacheValidatorPort;
import com.br.thaua.reservation_service.core.cache.validators.RoomCacheValidatorPort;
import com.br.thaua.reservation_service.core.repository.ParticipantRepositoryPort;
import com.br.thaua.reservation_service.core.repository.ReservationRepositoryPort;
import com.br.thaua.reservation_service.core.services.ParticipantServicePort;
import com.br.thaua.reservation_service.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ParticipantServiceAdapter implements ParticipantServicePort {
    private final ReservationRepositoryPort reservationRepositoryPort;
    private final ParticipantRepositoryPort participantRepositoryPort;
    private final AuthCacheValidatorPort authCacheValidatorPort;
    private final RoomCacheValidatorPort roomCacheValidatorPort;
    private final AuthCachePort authCachePort;
    private final RoomCachePort roomCachePort;

    @Override
    public Participant fetchParticipantOfReservation(Long participantId, Long reservationId) {
        return participantRepositoryPort.findByAuthIdAndReservationId(participantId, reservationId);
    }

    @Override
    public List<Participant> addNewParticipantsForReservation(List<Participant> participants, Long reservationId, Long hostId) {
        Reservation reservation = reservationRepositoryPort.findById(reservationId);

        if(reservation == null) {
            return null;
        }

        Participant host = participantRepositoryPort.findByAuthIdAndTypeParticipantAndReservation(hostId, TypeParticipant.HOST, reservation);

        if(host == null) {
            return null;
        }

        Room room = roomCachePort.getCacheRoom(reservation.getRoomId());
        room = roomCacheValidatorPort.validateRoomCache(reservation.getRoomId(), room);

        if(reservation.getParticipants() + participants.size() > room.getCapacity()) {
            return null;
        }

        for(Participant participant : participants) {
            Auth authCache = authCachePort.getCacheAuth(participant.getEmail());
            authCache = authCacheValidatorPort.validateAuthCache(participant.getEmail(), authCache);
            participant.setAuthId(authCache.getId());
            participant.setCheackin(false);
            participant.setReservation(reservation);
        }
        return participantRepositoryPort.saveAll(participants);
    }

    @Override
    public List<Participant> listParticipantsByReservationId(Long reservationId) {
        return participantRepositoryPort.findAllByReservationId(reservationId);
    }

    @Override
    public List<Participant> listParticipantsOfOneTypeByReservationId(TypeParticipant typeParticipant, Long reservationId) {
        return participantRepositoryPort.findAllByTypeParticipantAndReservationId(typeParticipant, reservationId);
    }

    @Override
    public List<Participant> listParticipantsWithSpecificCheackinByReservationId(boolean cheackin, Long reservationId) {
        return participantRepositoryPort.findAllByCheackinAndReservationId(cheackin, reservationId);
    }

}
