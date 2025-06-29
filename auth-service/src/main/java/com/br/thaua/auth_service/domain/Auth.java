package com.br.thaua.auth_service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Auth {
    private Long id;
    private String name;
    private String email;
    private String password;
    private List<Role> roles;
}
