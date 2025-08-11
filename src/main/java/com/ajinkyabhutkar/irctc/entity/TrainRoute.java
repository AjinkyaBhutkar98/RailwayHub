package com.ajinkyabhutkar.irctc.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity(name="irctc_train_route")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TrainRoute {


    //many trains can arrive at one route
    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //many routes can reach one station
    @ManyToOne
    @JoinColumn(name="station_id")
    private Station station;

    private Integer stationOrder;

    private LocalTime arrivalTime;

    private LocalTime departureTime;

    private LocalTime haltTime;

    private Integer distanceFromSource;
}
