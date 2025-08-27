package com.br.thaua.reservation_service.services.jobs;

import com.br.thaua.reservation_service.domain.EventType;
import com.br.thaua.reservation_service.domain.Reservation;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JobManager {
    @Value("${scheduler.group.name}")
    private String schedulerGroup;

    @Value("${trigger.group.name}")
    private String triggerGroup;

    private final Scheduler scheduler;

    public void scheduleReservation(Reservation reservation, EventType eventType) {
        JobDetail job;
        LocalDateTime triggerStartAt;
        Trigger trigger;

        switch (eventType) {
            case RESERVATION_BEGIN -> {
                job = createJob(reservation.getId(), reservation.getRoomId());
                triggerStartAt = toLocalDateTime(reservation.getDate(), reservation.getStart());
                trigger = createTrigger(job, triggerStartAt, reservation.getId(), JobKeyEnum.START_KEY);
            }
            case RESERVATION_FINISHED -> {
                job = createJob(reservation.getId());
                triggerStartAt = toLocalDateTime(reservation.getDate(), reservation.getEnd());
                trigger = createTrigger(job, triggerStartAt, reservation.getId(), JobKeyEnum.FINISH_KEY);
            }

            default -> {
                return;
            }
        }

        try {
            scheduler.scheduleJob(job, trigger);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateScheduler(Reservation reservation, JobKeyEnum jobKeyEnum) {
        deleteScheduler(reservation.getId(), jobKeyEnum);
        scheduleReservation(reservation, EventType.RESERVATION_FINISHED);

    }

    public void deleteScheduler(Long reservationId, JobKeyEnum jobKeyEnum) {
        JobKey deletedJobKey = createJobKey(reservationId, jobKeyEnum);
        try {
            if(scheduler.checkExists(deletedJobKey)) {
                scheduler.deleteJob(deletedJobKey);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private JobDetail createJob(Long reservationId) {
        return JobBuilder.newJob(FinishReservationJob.class)
                .usingJobData("reservationId", reservationId)
                .withIdentity(createJobKey(reservationId, JobKeyEnum.START_KEY))
                .build();
    }

    private JobDetail createJob(Long reservationId, Long roomId) {
        return JobBuilder.newJob(BeginReservationJob.class)
                .usingJobData("reservationId", reservationId)
                .usingJobData("roomId", roomId)
                .withIdentity(createJobKey(reservationId, JobKeyEnum.FINISH_KEY))
                .build();
    }

    private Trigger createTrigger(JobDetail job, LocalDateTime startAt, Long reservationId, JobKeyEnum jobKeyEnum) {
        return TriggerBuilder.newTrigger()
                .forJob(job)
                .withIdentity(createTriggerKey(reservationId, jobKeyEnum))
                .startAt(Date.from(startAt.atZone(ZoneId.systemDefault()).toInstant()))
                .build();
    }

    private JobKey createJobKey(Long reservationId, JobKeyEnum jobKeyEnum) {
        return JobKey.jobKey(jobKeyEnum.getType() + ":"+ reservationId.toString(), schedulerGroup);
    }

    private TriggerKey createTriggerKey(Long reservationId, JobKeyEnum jobKeyEnum) {
        return TriggerKey.triggerKey(jobKeyEnum.getType() + ":" + reservationId.toString(), triggerGroup);
    }

    private LocalDateTime toLocalDateTime(LocalDate date, LocalTime time) {
        return LocalDateTime.of(date, time).withSecond(0);
    }
}
