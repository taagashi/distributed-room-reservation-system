package com.br.thaua.room_service.persistence.models;

import com.br.thaua.room_service.domain.FeedBackType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "feedBack_tb")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class FeedBackEntity extends AbstractEntity{
    private Long authorId;
    private String author;
    private String feedBack;

    @Enumerated(EnumType.STRING)
    private FeedBackType feedBackType;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;

    @UpdateTimestamp
    private LocalDateTime date;
}
