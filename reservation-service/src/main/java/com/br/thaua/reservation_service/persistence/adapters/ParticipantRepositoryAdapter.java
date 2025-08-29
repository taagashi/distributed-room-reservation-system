package com.br.thaua.reservation_service.persistence.adapters;

import com.br.thaua.reservation_service.core.repository.ParticipantRepositoryPort;
import com.br.thaua.reservation_service.domain.Participant;
import com.br.thaua.reservation_service.domain.Reservation;
import com.br.thaua.reservation_service.domain.TypeParticipant;
import com.br.thaua.reservation_service.persistence.ParticipantRepository;
import com.br.thaua.reservation_service.persistence.mappers.ParticipantMapper;
import com.br.thaua.reservation_service.persistence.mappers.ReservationMapper;
import com.br.thaua.reservation_service.persistence.models.ParticipantEntity;
import com.br.thaua.reservation_service.persistence.models.ReservationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantRepositoryAdapter implements ParticipantRepositoryPort {
    private final ParticipantRepository participantRepository;
    private final ParticipantMapper participantMapper;
    private final ReservationMapper reservationMapper;

    @Override
    public Participant save(Participant participant) {
        ParticipantEntity saved  = participantMapper.map(participant);
        return participantMapper.map(participantRepository.save(saved));
    }

    @Override
    public List<Participant> saveAll(List<Participant> participants) {
        List<ParticipantEntity> saves = participants.stream().map(participantMapper::map).toList();
        return participantRepository.saveAll(saves).stream().map(participantMapper::map).toList();
    }

    @Override
    public Participant findByAuthIdAndTypeParticipantAndReservation(Long authId, TypeParticipant typeParticipant, Reservation reservation) {
        ReservationEntity reservationEntity = reservationMapper.map(reservation);
        return participantMapper.map(participantRepository.findByAuthIdAndTypeParticipantAndReservation(authId, typeParticipant, reservationEntity));
    }

    @Override
    public Participant findByAuthIdAndReservationId(Long participantId, Long reservationId) {
        return participantMapper.map(participantRepository.findByAuthIdAndReservationId(participantId, reservationId));
    }

    @Override
    public Participant findByAuthIdAndTypeParticipantAndReservationId(Long authId, TypeParticipant typeParticipant, Long reservationId) {
        return participantMapper.map(participantRepository.findByAuthIdAndTypeParticipantAndReservationId(authId, typeParticipant, reservationId));
    }

    @Override
    public List<Participant> findAllByReservation(Reservation reservation) {
        List<ParticipantEntity> participants =  participantRepository.findAllByReservation(reservationMapper.map(reservation));
        return participants.stream().map(participantMapper::map).toList();
    }

    @Override
    public List<Participant> findAllByTypeParticipantAndReservationId(TypeParticipant typeParticipant, Long reservationId) {
        List<ParticipantEntity> participants = participantRepository.findAllByTypeParticipantAndReservationId(typeParticipant, reservationId);
        return participants.stream().map(participantMapper::map).toList();
    }

    @Override
    public List<Participant> findAllByCheackinAndReservationId(boolean cheackin, Long reservationId) {
        return participantRepository.findAllByCheackinAndReservationId(cheackin, reservationId).stream().map(participantMapper::map).toList();
    }

    @Override
    public List<Reservation> findAllReservationByAuthId(Long authId) {
        List<ReservationEntity> reservations = participantRepository.findAllReservationByAuthId(authId);
        return reservations.stream().map(reservationMapper::map).toList();
    }

    @Override
    public void deleteAllById(List<Long> participantsIds) {

    }

    @Override
    public void deleteAllByAuthId(Long id) {

    }

    @Override
    public void updateEmailByAuthId(Long id, String email) {

    }

    @Override
    public List<Participant> findAllByReservationId(Long reservationId) {
        return participantRepository.findAllByReservationId(reservationId).stream().map(participantMapper::map).toList();
    }
}
