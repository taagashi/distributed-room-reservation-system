package com.br.thaua.employee_service.persistence.models;

import com.br.thaua.employee_service.domain.FeedBackType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "feedBack_tb")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FeedBackEntity extends AbstractEntity {
    @ManyToOne
    private EmployeeEntity author;
    private String feedBack;

    @Enumerated(EnumType.STRING)
    private FeedBackType feedBackType;

    @ManyToOne
    private EmployeeEntity receiver;

    @UpdateTimestamp
    private LocalDateTime date;
}
