package com.br.thaua.employee_service.controllers.dto;

public record EmployeeResponse(Long id, String name, String email, int age, String department) {}