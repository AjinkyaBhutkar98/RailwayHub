package com.ajinkyabhutkar.irctc.controller.admin;

import com.ajinkyabhutkar.irctc.dto.TrainSeatDto;
import com.ajinkyabhutkar.irctc.service.TrainSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/train-seats")
public class TrainSeatController {

    private TrainSeatService trainSeatService;

    @Autowired
    public TrainSeatController(TrainSeatService trainSeatService) {
        this.trainSeatService = trainSeatService;
    }

    //create seat
    @PostMapping
    public ResponseEntity<TrainSeatDto> createTrainSeat(@RequestBody TrainSeatDto trainSeatDto){

        return new ResponseEntity<>(trainSeatService.createSeatInfo(trainSeatDto),HttpStatus.CREATED);
    }

    //get coach of train schedule
    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<List<TrainSeatDto>> getSeatsByScheduleId(
             @PathVariable Long scheduleId) {
        List<TrainSeatDto> seats = trainSeatService.getSeatInfoByTrainScheduleId(scheduleId);
        return ResponseEntity.ok(seats);
    }

    //delete seat info
    @DeleteMapping("/{seatId}")
    public ResponseEntity<Void> deleteSeatInfo(@PathVariable Long seatId) {
        trainSeatService.deleteSeatInfo(seatId);
        return ResponseEntity.noContent().build();
    }

    //update seat info
    @PutMapping("/{seatId}")
    public ResponseEntity<TrainSeatDto> updateSeatInfo(
            @PathVariable Long seatId,
            @RequestBody TrainSeatDto trainSeatDto) {
        TrainSeatDto updatedSeat = trainSeatService.updateSeatsinfo(seatId, trainSeatDto);
        return ResponseEntity.ok(updatedSeat);
    }
}
