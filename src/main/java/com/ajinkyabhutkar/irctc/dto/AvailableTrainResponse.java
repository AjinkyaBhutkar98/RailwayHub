package com.ajinkyabhutkar.irctc.dto;

import com.ajinkyabhutkar.irctc.entity.CoachType;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AvailableTrainResponse {

    private Long trainId;
    private String trainName;
    private String trainNumber;
    private LocalTime arrivalTime;
    private LocalTime departureTime;

    //coach and available seats
    private Map<CoachType,Integer> seatsAvailable;

    //price by coach type
    private Map<CoachType,Double> priceByCoach;

 }
