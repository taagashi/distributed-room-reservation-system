package com.br.thaua.employee_service.http.dto;

public record EmployeeResponse(Long id, String name, String email, int age, String department) {}