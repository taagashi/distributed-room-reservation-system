package com.br.thaua.room_service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Room {
    private Long id;
    private String name;
    private int capacity;
}
