package com.ajinkyabhutkar.irctc.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name="irctc_train_seats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainSeats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //many seats will be available for scheduled train
    @ManyToOne
    @JoinColumn(name = "train_schedule_id")
    private TrainSchedule trainSchedule;

    @Enumerated(EnumType.STRING)
    private CoachType coachType;

    private Integer totalSeats;

    private Integer availableSeats;

    //seats will start from 1
    //if 3 out of 50 books next booking will start from 4
    private Integer nextToAssign=1;

    private BigDecimal price;
}

