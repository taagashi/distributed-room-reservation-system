package com.br.thaua.room_service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class FeedBack {
    private Long id;
    private Long authorId;
    private String author;
    private String feedBack;
    private FeedBackType feedBackType;
    private LocalDateTime date;
}
