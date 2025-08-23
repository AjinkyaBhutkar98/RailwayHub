package com.ajinkyabhutkar.irctc.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "irctc_train_schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime runDate;

    private Integer availableSeats;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    //types of seat
    //train cha dabba
    @OneToMany(mappedBy = "trainSchedule")
    private List<TrainSeats> trainSeats;

    //booking

    @OneToMany(mappedBy = "trainSchedule")
    private List<Booking> bookings;


}
