package com.br.thaua.employee_service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FavRoom {
    private Long id;
    private Integer roomNumber;
    private String state;
    private Employee employee;
}
