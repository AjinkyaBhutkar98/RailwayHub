package com.ajinkyabhutkar.irctc.dto;

import com.ajinkyabhutkar.irctc.entity.Station;
import com.ajinkyabhutkar.irctc.entity.Train;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainRouteDto {

    private Long id;
    private TrainDTO train;
    private StationDto station;
    private Integer stationOrder;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
    private Integer haltTime;
    private Integer distanceFromSource;
}
