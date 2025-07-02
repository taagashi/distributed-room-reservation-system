package com.br.thaua.auth_service.messaging.dto;

import java.util.List;

public record AuthEvent(Long id, String name, String email, List<String> roles) {}