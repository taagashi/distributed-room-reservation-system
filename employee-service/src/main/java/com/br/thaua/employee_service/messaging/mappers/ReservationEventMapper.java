package com.br.thaua.employee_service.messaging.mappers;

import com.br.thaua.employee_service.domain.ReservationLog;
import com.br.thaua.employee_service.messaging.dto.ReservationEvent;
import com.br.thaua.employee_service.messaging.dto.ReservationEventUpdated;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationEventMapper {
    @Mapping(target = "employee.id", source = "authId")
    ReservationLog map(ReservationEvent reservationEvent);

    @Mapping(target = "employee.id", source = "authId")
    ReservationLog map(ReservationEventUpdated reservationEventUpdated);
}
