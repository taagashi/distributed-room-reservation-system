package com.br.thaua.room_service.http.dto;

import java.time.LocalDateTime;

public record FeedBackResponse(Long id,
                               Long authorId,
                               String author,
                               String feedBack,
                               String feedBackType,
                               LocalDateTime date
                               ) {
}
