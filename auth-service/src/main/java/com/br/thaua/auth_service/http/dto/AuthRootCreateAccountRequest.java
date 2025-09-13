package com.br.thaua.auth_service.http.dto;

import java.util.List;

public record AuthRootCreateAccountRequest(String name, String email, String password, List<String> roles) {}
