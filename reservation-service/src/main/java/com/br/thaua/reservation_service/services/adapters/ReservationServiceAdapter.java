package com.br.thaua.reservation_service.services.adapters;

import com.br.thaua.reservation_service.core.cache.RoomCachePort;
import com.br.thaua.reservation_service.core.cache.validators.RoomCacheValidatorPort;
import com.br.thaua.reservation_service.core.messaging.publishers.ReservationEventPublisherPort;
import com.br.thaua.reservation_service.core.repository.ParticipantRepositoryPort;
import com.br.thaua.reservation_service.core.repository.ReservationRepositoryPort;
import com.br.thaua.reservation_service.core.services.ReservationServicePort;
import com.br.thaua.reservation_service.domain.*;
import com.br.thaua.reservation_service.messaging.mappers.ReservationEventMapper;
import com.br.thaua.reservation_service.services.jobs.JobKeyEnum;
import com.br.thaua.reservation_service.services.jobs.JobManager;
import com.br.thaua.reservation_service.services.validators.ReservationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceAdapter implements ReservationServicePort {
    private final ReservationRepositoryPort reservationRepositoryPort;
    private final ParticipantRepositoryPort participantRepositoryPort;
    private final RoomCachePort roomCachePort;
    private final RoomCacheValidatorPort roomCacheValidatorPort;
    private final ReservationEventPublisherPort reservationEventPublisherPort;
    private final ReservationEventMapper reservationEventMapper;
    private final JobManager jobManager;
    private final ReservationValidator reservationValidator;

    @Override
    public Reservation addNewReservation(Reservation reservation, Long authId, String email) {
        reservationValidator.validateDateTimeReservation(reservation.getDate(), reservation.getStart(), reservation.getEnd());

        List<Reservation> reservationExists = reservationRepositoryPort.findAllByRoomIdAndDateAndStartLessThanEqualAndEndGreaterThanEqual(reservation.getRoomId(), reservation.getDate(), reservation.getEnd(), reservation.getStart());

        if(!reservationExists.isEmpty()) {
            throw new RuntimeException("reserva nesse horario");
        }

        Room room = roomCachePort.getCacheRoom(reservation.getRoomId());
        room = roomCacheValidatorPort.validateRoomCache(reservation.getRoomId(), room);

        reservation.setRoomNumber(room.getRoomNumber());
        Reservation saved =  reservationRepositoryPort.save(reservation);
        participantRepositoryPort.save(new Participant(null, authId, email, TypeParticipant.HOST, saved, false));

        jobManager.scheduleReservation(saved, EventType.RESERVATION_BEGIN);
        jobManager.scheduleReservation(saved, EventType.RESERVATION_FINISHED);
        reservationEventPublisherPort.sendToReservationExchange(reservationEventMapper.map(saved, EventType.RESERVATION_CREATED, authId));
        return saved;
        }

    @Override
    public Reservation updateReservation(Long reservationId, Long hostId, Reservation reservation) {
       reservationValidator.validateDateTimeReservation(reservation.getDate(), reservation.getStart(), reservation.getEnd());

       Reservation updated = reservationRepositoryPort.findById(reservationId);

       if(updated == null) {
        throw new RuntimeException("Reservation not found");
       }

       Participant host = participantRepositoryPort.findByAuthIdAndTypeParticipantAndReservation(hostId, TypeParticipant.HOST, updated);

       if(host == null) {
           throw new RuntimeException("host not found");
       }

       Room room = roomCachePort.getCacheRoom(reservation.getRoomId());
//       roomCacheValidatorPort.validateRoomCache(reservation.getRoomId(), room);

       LocalDate oldDate = updated.getDate();

       updated.setRoomId(room.getId());
       updated.setRoomNumber(room.getRoomNumber());
       updated.setDate(reservation.getDate());
       updated.setStart(reservation.getStart());
       updated.setEnd(reservation.getEnd());

       if(!oldDate.equals(reservation.getDate())) {
           reservationEventPublisherPort.sendToReservationExchange(reservationEventMapper.map(updated, EventType.RESERVATION_UPDATED, hostId));
       }

       updated = reservationRepositoryPort.save(updated);

       jobManager.updateScheduler(updated, JobKeyEnum.START_KEY);
       jobManager.updateScheduler(updated, JobKeyEnum.FINISH_KEY);
       return updated;
    }

    @Override
    public Reservation fetchReservationById(Long id) {
        return reservationRepositoryPort.findById(id);
    }

    @Override
    public List<Reservation> fetchAllReservation() {
        return reservationRepositoryPort.findAll();
    }

    @Override
    public List<Reservation> fetchAllReservationByEmployee(Long authId) {
        return participantRepositoryPort.findAllReservationByAuthId(authId);
    }

    @Override
    public void deleteReservationById(Long reservationId, Long hostId) {
        Reservation deleted = reservationRepositoryPort.findById(reservationId);

        Participant host = participantRepositoryPort.findByAuthIdAndTypeParticipantAndReservation(hostId, TypeParticipant.HOST, deleted);

        if(host == null) {
            throw new RuntimeException("invalid host");
        }

        reservationRepositoryPort.delete(deleted);
        reservationEventPublisherPort.sendToReservationExchange(reservationEventMapper.map(deleted, EventType.RESERVATION_FINISHED, hostId));
        jobManager.deleteScheduler(reservationId, JobKeyEnum.START_KEY);
        jobManager.deleteScheduler(reservationId, JobKeyEnum.FINISH_KEY);

    }
}
