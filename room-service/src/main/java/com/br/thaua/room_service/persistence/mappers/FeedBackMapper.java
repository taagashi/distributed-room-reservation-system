package com.br.thaua.room_service.persistence.mappers;

import com.br.thaua.room_service.domain.FeedBack;
import com.br.thaua.room_service.persistence.models.FeedBackEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeedBackMapper {
    FeedBack map(FeedBackEntity feedBackEntity);
    FeedBackEntity map(FeedBack feedBack);
}
