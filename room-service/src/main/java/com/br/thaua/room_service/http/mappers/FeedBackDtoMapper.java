package com.br.thaua.room_service.http.mappers;

import com.br.thaua.room_service.domain.FeedBack;
import com.br.thaua.room_service.http.dto.FeedBackRequest;
import com.br.thaua.room_service.http.dto.FeedBackResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeedBackDtoMapper {
    FeedBack map(FeedBackRequest feedBackRequest);
    FeedBackResponse map(FeedBack feedBack);
}
