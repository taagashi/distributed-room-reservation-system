package com.br.thaua.reservation_service.persistence.mappers;

import com.br.thaua.reservation_service.domain.Participant;
import com.br.thaua.reservation_service.persistence.models.ParticipantEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParticipantMapper {
    Participant map(ParticipantEntity participantEntity);
    ParticipantEntity map(Participant participant);
}
