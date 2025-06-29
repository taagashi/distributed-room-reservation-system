package com.br.thaua.auth_service.http.dto;

import java.util.List;

public record AuthResponse(Long id, String name, String email, List<String> roles) {}
