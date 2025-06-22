package com.br.thaua.employee_service.controllers.dto;

public record EmployeeEvent(Long id, String name, String email, int age, String department) {}