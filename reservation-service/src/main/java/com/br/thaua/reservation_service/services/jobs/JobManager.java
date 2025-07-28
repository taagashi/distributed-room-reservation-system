package com.br.thaua.reservation_service.services.jobs;

import com.br.thaua.reservation_service.core.repository.ReservationRepositoryPort;
import com.br.thaua.reservation_service.core.services.ReservationServicePort;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JobManager implements Job {
    private final ReservationRepositoryPort reservationRepositoryPort;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Long reservationId = jobExecutionContext.getJobDetail().getJobDataMap().getLong("reservationId");
        reservationRepositoryPort.deleteById(reservationId);
    }
}
