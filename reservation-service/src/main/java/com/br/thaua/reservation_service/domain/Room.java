package com.br.thaua.reservation_service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    private Long id;
    private String name;
    private int capacity;
}
