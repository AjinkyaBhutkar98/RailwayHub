package com.ajinkyabhutkar.irctc.dto;

import com.ajinkyabhutkar.irctc.entity.Booking;
import com.ajinkyabhutkar.irctc.entity.Train;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainScheduleDto {

    private Long id;
    private Long trainId;
    private LocalDate runDate;
    private Integer availableSeats;
}
