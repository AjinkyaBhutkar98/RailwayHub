package com.ajinkyabhutkar.irctc.controller.admin;

import com.ajinkyabhutkar.irctc.dto.PagedResponse;
import com.ajinkyabhutkar.irctc.dto.StationDto;
import com.ajinkyabhutkar.irctc.service.StationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/stations")
public class StationController {



    private StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @PostMapping
    public ResponseEntity<StationDto> createStation(
            @Valid
            @RequestBody StationDto stationDto
    ){

        StationDto dto= stationService.createStation(stationDto);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }

    @GetMapping
    public PagedResponse<StationDto> getAllStations(
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "10") int size,
            @RequestParam(name = "sortBy",defaultValue = "name") String sortBy,
            @RequestParam(name = "sortDir",defaultValue = "asc") String sortDir

    ){
        PagedResponse<StationDto> stationsDtos=stationService.getAllStations(page, size, sortBy, sortDir);
        return stationsDtos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StationDto> getStationById(@PathVariable long id){

        return new ResponseEntity<>(stationService.getStationById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StationDto> updateStation(@PathVariable long id,@RequestBody StationDto stationDto){

        StationDto updatedStation=stationService.updateStation(id,stationDto);

        return new ResponseEntity<>(updatedStation,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStation(@PathVariable long id){

        stationService.deleteStation(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
