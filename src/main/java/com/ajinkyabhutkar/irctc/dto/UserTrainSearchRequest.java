package com.ajinkyabhutkar.irctc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserTrainSearchRequest {

    private Long stationId;
    private long destinationId;
    private LocalDate journeyDate;

}
