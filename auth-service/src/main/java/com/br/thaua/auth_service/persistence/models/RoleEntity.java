package com.br.thaua.auth_service.persistence.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "role_tb")
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity {
    @Id
    private String role;

    @ManyToMany(mappedBy = "roles")
    private List<AuthEntity> authEntity;
}
