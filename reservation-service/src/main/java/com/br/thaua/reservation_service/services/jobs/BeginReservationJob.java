package com.br.thaua.reservation_service.services.jobs;

import com.br.thaua.reservation_service.core.messaging.publishers.ReservationEventPublisherPort;
import com.br.thaua.reservation_service.core.repository.ParticipantRepositoryPort;
import com.br.thaua.reservation_service.core.repository.ReservationRepositoryPort;
import com.br.thaua.reservation_service.domain.EventType;
import com.br.thaua.reservation_service.domain.Participant;
import com.br.thaua.reservation_service.messaging.dto.publisher.AuthEventPublisher;
import com.br.thaua.reservation_service.messaging.dto.publisher.RoomEventPublisher;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BeginReservationJob implements Job {
    private final ReservationEventPublisherPort reservationEventPublisherPort;
    private final ReservationRepositoryPort reservationRepositoryPort;
    private final ParticipantRepositoryPort participantRepositoryPort;
    private final JobManager jobManager;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        Long reservationId = jobExecutionContext.getJobDetail().getJobDataMap().getLong("reservationId");
        Long roomId = jobExecutionContext.getJobDetail().getJobDataMap().getLong("roomId");

        List<Participant> participants = participantRepositoryPort.findAllByReservationId(reservationId);

        boolean haveCheackin = verifyParticipantsCheackin(participants);

        if(!haveCheackin) {
            jobManager.deleteScheduler(reservationId, JobKeyEnum.START_KEY);
            reservationRepositoryPort.deleteById(reservationId);
            return;
        }

        reservationEventPublisherPort.sendToReservationExchange(new RoomEventPublisher(EventType.RESERVATION_BEGIN, roomId));
    }

    private boolean verifyParticipantsCheackin(List<Participant> participants) {
        boolean haveCheackin = false;

        for(Participant participant : participants) {
            if(participant.isCheackin()) {
                haveCheackin = true;
                reservationEventPublisherPort.sendToReservationExchange(new AuthEventPublisher(EventType.RESERVATION_EMPLOYEE_PRESENT, participant.getTypeParticipant(), participant.getAuthId()));
            }

            reservationEventPublisherPort.sendToReservationExchange(new AuthEventPublisher(EventType.RESERVATION_EMPLOYEE_NOT_PRESENT, participant.getTypeParticipant(), participant.getAuthId()));
        }
        return haveCheackin;
    }
}
