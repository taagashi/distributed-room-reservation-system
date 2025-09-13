package com.br.thaua.employee_service.domain;

import com.br.thaua.employee_service.core.repository.EmployeeRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Setter
public class FeedBack {
    private final EmployeeRepositoryPort employeeRepositoryPort;
    private Long id;
    private Employee author;
    private String feedBack;
    private FeedBackType feedBackType;
    private Employee receiver;
    private LocalDateTime date;

    public void verifyAuthorReceiver() {
        if(author.equals(receiver)) {
            throw new RuntimeException();
        }
    }

    public void defineAuthor(Employee employee) {
        this.author = employee;
    }

    public void defineReceiver(Employee receiver) {
        this.receiver = receiver;
    }
}
