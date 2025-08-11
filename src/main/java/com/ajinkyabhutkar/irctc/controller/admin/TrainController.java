package com.ajinkyabhutkar.irctc.controller.admin;


import com.ajinkyabhutkar.irctc.dto.PagedResponse;
import com.ajinkyabhutkar.irctc.dto.StationDto;
import com.ajinkyabhutkar.irctc.dto.TrainDTO;
import com.ajinkyabhutkar.irctc.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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



    @GetMapping("/{id}")
    public ResponseEntity<TrainDTO> getTrain(@PathVariable Long id){

        return  new ResponseEntity<>(trainService.getTrain(id),HttpStatus.OK);

    }

    @GetMapping
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
