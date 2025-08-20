package com.br.thaua.reservation_service.persistence.mappers;

import com.br.thaua.reservation_service.domain.TypeParticipant;
import com.br.thaua.reservation_service.persistence.models.TypeParticipantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TypeParticipantMapper{
    default TypeParticipantEntity map(TypeParticipant typeParticipant) {
        return new TypeParticipantEntity(typeParticipant.getId(), typeParticipant.name());
    }
}
