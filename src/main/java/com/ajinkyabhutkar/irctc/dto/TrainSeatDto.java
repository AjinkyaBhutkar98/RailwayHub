package com.ajinkyabhutkar.irctc.dto;

import com.ajinkyabhutkar.irctc.entity.CoachType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainSeatDto {

    private Long id;
    private Long trainScheduleId;
    private CoachType coachType;
    private Integer totalSeats;
    private Integer availableSeats;
    private Double price;
    private Integer seatNumberToAssign;
    private Integer seatOrder;

}
