package com.ajinkyabhutkar.irctc.controller.admin;


import com.ajinkyabhutkar.irctc.dto.TrainScheduleDto;
import com.ajinkyabhutkar.irctc.service.TrainScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/train-schedules")
public class TrainScheduleController {

    private TrainScheduleService trainScheduleService;

    @Autowired
    public TrainScheduleController(TrainScheduleService trainScheduleService) {
        this.trainScheduleService = trainScheduleService;
    }

    @PostMapping
    public ResponseEntity<TrainScheduleDto> createTrainSchedule(@RequestBody TrainScheduleDto trainScheduleDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(trainScheduleService.createSchedule(trainScheduleDto));
    }

    @GetMapping("/train/{trainId}")
    public ResponseEntity<List<TrainScheduleDto>> findTrainSchedule(@PathVariable Long trainId){

        List<TrainScheduleDto> getTrainScheduleDto=trainScheduleService.getTrainScheduleDtoById(trainId);

        return ResponseEntity.status(HttpStatus.OK).body(getTrainScheduleDto);
    }

    @DeleteMapping("/train/{trainId}")
    public ResponseEntity<TrainScheduleDto> deleteTrainSchedule(@PathVariable Long trainId){

        trainScheduleService.deleteSchedule(trainId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/train/{trainId}")
    public ResponseEntity<TrainScheduleDto> updateTrainSchedule(@PathVariable Long trainId,@RequestBody TrainScheduleDto trainScheduleDto){

        TrainScheduleDto trainScheduleDto1=trainScheduleService.updateTrainScheduleDto(trainId,trainScheduleDto);

        return ResponseEntity.status(HttpStatus.OK).body(trainScheduleDto1);
    }

}
