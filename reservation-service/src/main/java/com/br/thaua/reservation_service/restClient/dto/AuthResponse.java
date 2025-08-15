package com.br.thaua.reservation_service.restClient.dto;

import java.util.List;

public record AuthResponse(Long id, String name, String email, List<String> roles) {}
