package com.ajinkyabhutkar.irctc.controller.user;

import com.ajinkyabhutkar.irctc.dto.AvailableTrainResponse;
import com.ajinkyabhutkar.irctc.dto.UserTrainSearchRequest;
import com.ajinkyabhutkar.irctc.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/trains")
public class TrainSearchController {

    private TrainService trainService;

    @Autowired
    public TrainSearchController(TrainService trainService) {
        this.trainService = trainService;
    }



}
