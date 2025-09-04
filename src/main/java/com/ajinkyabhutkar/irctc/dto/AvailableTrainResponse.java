package com.ajinkyabhutkar.irctc.dto;

import com.ajinkyabhutkar.irctc.entity.CoachType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvailableTrainResponse {

    private Long trainId;
    private String trainName;
    private String trainNumber;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;

    //coach and available seats
    private Map<CoachType,Integer> seatsAvailable;

    //price by coach type
    private Map<String,Integer> priceByCoach;

 }
