package com.ajinkyabhutkar.irctc.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StationDto {

    private long id;

    @NotBlank(message = "Station code should not be blank")
    @Size(min = 3,max=10)
    private String code;
    @NotBlank(message = "station name cannot be blank")
    private String name;
    private String city;
    private String state;
}
