package com.br.thaua.employee_service.persistence.mappers;

import com.br.thaua.employee_service.domain.ReservationLog;
import com.br.thaua.employee_service.persistence.models.ReservationLogEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationLogMapper {
    ReservationLog map(ReservationLogEntity reservationLogEntity);
    ReservationLogEntity map(ReservationLog reservationLog);
}
