package com.br.thaua.reservation_service.http.mappers;

import com.br.thaua.reservation_service.domain.Reservation;
import com.br.thaua.reservation_service.http.dto.ReservationRequest;
import com.br.thaua.reservation_service.http.dto.ReservationResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationDtoMapper {
    Reservation map(ReservationRequest reservationRequest);
    ReservationResponse map(Reservation reservation);
}
