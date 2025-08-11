package com.ajinkyabhutkar.irctc.dto;

import com.ajinkyabhutkar.irctc.entity.Station;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TrainDTO {

    @NotEmpty(message = "Train no cannot be empty !!")
    @Size(min=1,max=10)
//    @Pattern(regexp = "^\\d+$",message = "Train no pattern failed pls try again")
    private Long id;

    private String number;

    private String name;

    private Integer totalTravelDistance;

    private StationDto destinationStation;

    private StationDto sourceStation;

}
