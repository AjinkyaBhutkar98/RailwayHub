package com.ajinkyabhutkar.irctc.service;


import com.ajinkyabhutkar.irctc.dto.PagedResponse;
import com.ajinkyabhutkar.irctc.dto.StationDto;
import com.ajinkyabhutkar.irctc.entity.Station;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface StationService {

     StationDto createStation(StationDto stationDto);

     PagedResponse<StationDto> getAllStations(int page, int size, String sortBy, String sortDir);

     StationDto getStationById(Long id);

     StationDto updateStation(Long id,StationDto stationDto);

     void deleteStation(Long id);
}
