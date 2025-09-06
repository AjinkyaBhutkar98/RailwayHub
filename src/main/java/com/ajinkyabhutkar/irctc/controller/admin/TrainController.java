package com.ajinkyabhutkar.irctc.controller.admin;


import com.ajinkyabhutkar.irctc.dto.PagedResponse;
import com.ajinkyabhutkar.irctc.dto.StationDto;
import com.ajinkyabhutkar.irctc.dto.TrainDTO;
import com.ajinkyabhutkar.irctc.service.TrainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminTrainController")
@RequestMapping("/admin/trains")
public class TrainController {


    private TrainService trainService;

    @Autowired
    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @PostMapping
    @Operation(summary = "create train", description = "create train with this api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "train created successfully"),
            @ApiResponse(responseCode = "400",description = "bad request")
    })
    public ResponseEntity<TrainDTO> createTrain(@RequestBody TrainDTO trainDTO){

//        System.out.println(trainDTO.getDestinationStation().getId());

        return new ResponseEntity<>( trainService.addTrain(trainDTO), HttpStatus.CREATED);
    }



    @GetMapping("/{id}")
    @Operation(summary = "get train by id", description = "you can get details of train in this api by passing id")
    public ResponseEntity<TrainDTO> getTrain(@Parameter(description = "id of train") @PathVariable Long id){

        return  new ResponseEntity<>(trainService.getTrain(id),HttpStatus.OK);

    }

    @GetMapping
    @Operation(summary = "get all trains", description = "you can get details of all trains present in database with pagination")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200",description = "request successful"),
            @ApiResponse(responseCode = "400",description = "request is failed"),
    })
    public PagedResponse<TrainDTO> getAllTrains(
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "10") int size,
            @RequestParam(name = "sortBy",defaultValue = "name") String sortBy,
            @RequestParam(name = "sortDir",defaultValue = "asc") String sortDir
    ){
        PagedResponse<TrainDTO> trainDtos=trainService.getAllTrains(page, size, sortBy, sortDir);
        return trainDtos;

    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainDTO> updateTrain(@PathVariable(name = "id") Long id,@RequestBody TrainDTO trainDTO){

           return new ResponseEntity<>(trainService.updateTrain(id,trainDTO),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        trainService.deleteTrain(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<List<TrainDTO>> getTrainsByName(@PathVariable String name){

        System.out.println(name);
        List<TrainDTO> allTrainsDto=trainService.searchByName(name);

        allTrainsDto.forEach(x-> System.out.println(x.getName()));
        return ResponseEntity.ok(allTrainsDto);
    }

}
