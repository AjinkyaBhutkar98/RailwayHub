package com.ajinkyabhutkar.irctc.service.impl;

import com.ajinkyabhutkar.irctc.dto.PagedResponse;
import com.ajinkyabhutkar.irctc.dto.StationDto;
import com.ajinkyabhutkar.irctc.dto.TrainDTO;
import com.ajinkyabhutkar.irctc.entity.Station;
import com.ajinkyabhutkar.irctc.repo.StationRepo;
import com.ajinkyabhutkar.irctc.service.StationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StationServiceImpl implements StationService {

    private StationRepo stationRepo;

    private ModelMapper modelMapper;

    @Autowired
    public StationServiceImpl(StationRepo stationRepo, ModelMapper modelMapper) {
        this.stationRepo = stationRepo;
        this.modelMapper = modelMapper;
    }

    //create single station
    @Override
    public StationDto createStation(StationDto stationDto) {

        Station station=new Station();

        station=modelMapper.map(stationDto,Station.class);

        Station savedStation=stationRepo.save(station);

        StationDto stationDto1=modelMapper.map(savedStation,StationDto.class);

        return stationDto1;
    }

    //get all stations with pagination
    @Override
    public PagedResponse<StationDto> getAllStations(int page, int size, String sortBy, String sortDir) {

        Sort sort= sortDir.trim().equalsIgnoreCase("asc")? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        // pass page size(from method arguments) sort

        Pageable pageable= PageRequest.of(page,size,sort);
        //it will content data

        Page<Station> stationPage = stationRepo.findAll(pageable);

        Page<StationDto> page2=stationPage.map(station->modelMapper.map(station,StationDto.class));

        return PagedResponse.fromPage(page2);
    }

    @Override
    public StationDto getStationById(Long id) {

        Station station=stationRepo.findById(id).orElseThrow( ()-> new RuntimeException("Station not found!!"));

        return  modelMapper.map(station,StationDto.class);

    }

    @Override
    public StationDto updateStation(Long id,StationDto stationDto) {

        Station station=stationRepo.findById(id).orElseThrow( ()-> new RuntimeException("Station not found!!"));

        station.setCode(stationDto.getCode());
        station.setName(stationDto.getName());
        station.setCity(stationDto.getCity());
        station.setState(stationDto.getState());

        Station station1=stationRepo.save(station);

        return modelMapper.map(station,StationDto.class);

    }

    @Override
    public void deleteStation(Long id) {

        Station station=stationRepo.findById(id).orElseThrow( ()-> new RuntimeException("Station not found!!"));

        stationRepo.delete(station);

    }


}
