package com.ajinkyabhutkar.irctc.controller.admin;


import com.ajinkyabhutkar.irctc.dto.TrainDTO;
import com.ajinkyabhutkar.irctc.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("adminTrainController")
@RequestMapping("/admin/trains")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @PostMapping
    public ResponseEntity<TrainDTO> createTrain(@RequestBody TrainDTO trainDTO){

//        System.out.println(trainDTO.getDestinationStation().getId());

        return new ResponseEntity<>( trainService.addTrain(trainDTO), HttpStatus.CREATED);
    }
}
