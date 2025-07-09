package com.br.thaua.reservation_service.http.mappers;

import com.br.thaua.reservation_service.domain.Participant;
import com.br.thaua.reservation_service.http.dto.ParticipantRequest;
import com.br.thaua.reservation_service.http.dto.ParticipantResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ParticipantDtoMapper {
    Participant map(ParticipantRequest participantRequest);
    @Mapping(target = "reservationId", source = "reservation.id")
    ParticipantResponse map(Participant participant);
}
