package com.br.thaua.reservation_service.persistence.mappers;

import com.br.thaua.reservation_service.domain.Participant;
import com.br.thaua.reservation_service.domain.TypeParticipant;
import com.br.thaua.reservation_service.persistence.models.ParticipantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ParticipantMapper {
    @Mapping(target = "typeParticipant", expression = "java(toDomainTypeParticipant(participantEntity.getTypeParticipant()))")
    Participant map(ParticipantEntity participantEntity);

    @Mapping(target = "typeParticipant", expression = "java(toEntityTypeParticipant(participant.getTypeParticipant()))")
    ParticipantEntity map(Participant participant);

    default TypeParticipant toDomainTypeParticipant(TypeParticipantEntity entity) {
        if(entity == null) {
            return  null;
        }
        return TypeParticipant.valueOf(entity.getType());
    }

    default TypeParticipantEntity toEntityTypeParticipant(TypeParticipant domain) {
        if(domain == null) {
            return null;
        }
        return new TypeParticipantEntity(domain.getId(), domain.name());
    }
}
