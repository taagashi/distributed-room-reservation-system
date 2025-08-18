package com.br.thaua.reservation_service.messaging.mappers;

import com.br.thaua.reservation_service.domain.EventType;
import com.br.thaua.reservation_service.domain.Reservation;
import com.br.thaua.reservation_service.messaging.dto.publisher.ReservationEventPublisher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationEventMapper {
    @Mapping(target = "year", expression = "java(reservation.getDate().getYear())")
    @Mapping(target = "month", expression = "java(reservation.getDate().getMonthValue())")
    @Mapping(target = "eventType", source = "eventType")
    @Mapping(target = "employeeId", source = "employeeId")
    ReservationEventPublisher map(Reservation reservation, EventType eventType, Long employeeId);
}
