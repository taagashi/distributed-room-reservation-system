package com.br.thaua.employee_service.http.mappers;

import com.br.thaua.employee_service.domain.FeedBack;
import com.br.thaua.employee_service.http.dto.FeedBackRequest;
import com.br.thaua.employee_service.http.dto.FeedBackResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FeedbackDtoMapper {
    FeedBack map(FeedBackRequest feedBackRequest);

    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "author", source = "author.email")
    @Mapping(target = "to", source = "receiver.email")
    FeedBackResponse map(FeedBack feedBack);
}
