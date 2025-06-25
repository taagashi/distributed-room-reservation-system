package com.br.thaua.reservation_service.persistence.mappers;

import com.br.thaua.reservation_service.domain.Reservation;
import com.br.thaua.reservation_service.persistence.models.ReservationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationEntity map(Reservation reservation);
    Reservation map(ReservationEntity reservationEntity);
}
