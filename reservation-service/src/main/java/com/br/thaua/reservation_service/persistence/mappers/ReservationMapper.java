package com.br.thaua.reservation_service.persistence.mappers;

import com.br.thaua.reservation_service.domain.Reservation;
import com.br.thaua.reservation_service.persistence.models.ReservationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationEntity map(Reservation reservation);
    @Mapping(target = "participants", expression = "java((reservationEntity.getParticipantEntity() != null) ? reservationEntity.getParticipantEntity().size(): 0)")
    Reservation map(ReservationEntity reservationEntity);
}
