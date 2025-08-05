package com.ajinkyabhutkar.irctc.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class TrainDTO {

    @NotEmpty(message = "Train no cannot be empty !!")
    @Size(min=1,max=10)
//    @Pattern(regexp = "^\\d+$",message = "Train no pattern failed pls try again")
    @Id
    private String trainNo;

//    @Email
//    private String email;

//        @Pattern(regexp = "^[A-Za-z0-9\\s\\-]+$\n",message = "Invalid train name. only Alphabets and hyphen allowed !!")
    private String name;


//    @CheckCouch
//    private int coaches;

    private String routeName;

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }
}
