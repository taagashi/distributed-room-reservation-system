package com.br.thaua.employee_service.http.dto;

import java.time.LocalDateTime;

public record FeedBackResponse(
        Long id,
        Long authorId,
        String author,
        String feedBack,
        String feedBackType,
        String to,
        LocalDateTime date
) {
}
