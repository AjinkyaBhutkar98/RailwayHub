package com.ajinkyabhutkar.irctc.dto;

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
    private Map<String,Integer> seatsAvailable;
    private Map<String,Integer> priceByCoach;

 }
