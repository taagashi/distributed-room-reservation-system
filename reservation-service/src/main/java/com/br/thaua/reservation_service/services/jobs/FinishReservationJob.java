package com.br.thaua.reservation_service.services.jobs;

import com.br.thaua.reservation_service.core.repository.ReservationRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FinishReservationJob implements Job {
    private final ReservationRepositoryPort reservationRepositoryPort;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        Long reservationId = jobExecutionContext.getJobDetail().getJobDataMap().getLong("reservationId");
        reservationRepositoryPort.deleteById(reservationId);
    }
}
