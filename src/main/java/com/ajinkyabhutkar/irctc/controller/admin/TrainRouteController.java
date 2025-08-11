package com.ajinkyabhutkar.irctc.controller.admin;

import com.ajinkyabhutkar.irctc.dto.TrainRouteDto;
import com.ajinkyabhutkar.irctc.service.TrainRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/train-routes")
public class TrainRouteController {

    private TrainRouteService trainRouteService;

    @Autowired
    public TrainRouteController(TrainRouteService trainRouteService) {
        this.trainRouteService = trainRouteService;
    }

    //create train route
    @PostMapping
    public ResponseEntity<TrainRouteDto> createTrainRoute(@RequestBody TrainRouteDto trainRouteDto){

        TrainRouteDto train=trainRouteService.addTrainRoute(trainRouteDto);

//        return new ResponseEntity<>(train, HttpStatus.CREATED);

        return ResponseEntity.status(201).body(train);
    }

    //get all Train Route

    @GetMapping
    public ResponseEntity<List<TrainRouteDto>> getAllTrainRoutes(){

        return ResponseEntity.ok(trainRouteService.getAllTrainRoutes());
    }

    //get train routes by id
    @GetMapping("/{trainId}")
    public ResponseEntity<List<TrainRouteDto>> getSingleTrainRoute(@PathVariable Long trainId){

        List<TrainRouteDto> trainRouteDtos=trainRouteService.getRoutesByTrain(trainId);

        return ResponseEntity.ok(trainRouteDtos);
    }

    //update train route
    @PutMapping("/{trainId}")
    public ResponseEntity<TrainRouteDto> updateTrainRoute(@PathVariable Long trainId,@RequestBody TrainRouteDto trainRouteDto){

        return new ResponseEntity<>(trainRouteService.updateTrainRoute(trainId,trainRouteDto),HttpStatus.OK);
    }

    //delete train
    @DeleteMapping("/{trainId}")
    public ResponseEntity<Void> deleteTrain(@PathVariable Long trainId){

        trainRouteService.deleteRoute(trainId);

        return ResponseEntity.noContent().build();
    }

}
