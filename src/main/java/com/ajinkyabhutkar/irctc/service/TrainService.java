package com.ajinkyabhutkar.irctc.service;

import com.ajinkyabhutkar.irctc.dto.StationDto;
import com.ajinkyabhutkar.irctc.dto.TrainDTO;
import com.ajinkyabhutkar.irctc.entity.Station;
import com.ajinkyabhutkar.irctc.entity.Train;
import com.ajinkyabhutkar.irctc.exceptions.TrainNotFoundException;
import com.ajinkyabhutkar.irctc.repo.StationRepo;
import com.ajinkyabhutkar.irctc.repo.TrainRepo;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainService {


    private TrainRepo trainRepo;

    private StationService stationService;

    private  ModelMapper modelMapper;

    private List<Train> trainList=new ArrayList<>();


    @Autowired
    public TrainService(TrainRepo trainRepo, StationService stationService, ModelMapper modelMapper, List<Train> trainList) {
        this.trainRepo = trainRepo;
        this.stationService = stationService;
        this.modelMapper = modelMapper;
        this.trainList = trainList;
    }


    public TrainService(){

    }

    public TrainDTO addTrain(TrainDTO trainDTO){

        Train newTrain=new Train();

        StationDto sourceStationDto=stationService.getStationById(trainDTO.getSourceStation().getId());
        StationDto destinationStationDto=stationService.getStationById(trainDTO.getDestinationStation().getId());
        Station sourcestation=modelMapper.map(sourceStationDto,Station.class);
        Station destinationstation=modelMapper.map(destinationStationDto,Station.class);
        System.out.println(sourceStationDto.getId());

        newTrain.setName(trainDTO.getName());
        newTrain.setSourceStation(sourcestation);
        newTrain.setDestinationStation(destinationstation);
        newTrain.setNumber(trainDTO.getNumber());
        newTrain.setTotalTravelDistance(trainDTO.getTotalTravelDistance());


        Train train=trainRepo.save(newTrain);

        TrainDTO trainDTO1=modelMapper.map(train, TrainDTO.class);


       return trainDTO1;
    }

    public List<TrainDTO> allTrains() {
        List<Train> allTrains = trainRepo.findAll();

        List<TrainDTO> dtoTrains = allTrains.stream()
                .map(train -> modelMapper.map(train, TrainDTO.class))
                .toList();

        return dtoTrains;
    }


    public TrainDTO getTrain(Long trainNo){

        Train train=trainRepo.findById(trainNo).orElseThrow(()->new RuntimeException("Train not found!!"));

        return modelMapper.map(train, TrainDTO.class);

    }

    public TrainDTO delete(Long trainNo){

        Train train=trainRepo.findById(trainNo).orElseThrow(()->new RuntimeException("Train not found!!"));

        trainRepo.delete(train);

        return modelMapper.map(train,TrainDTO.class);
    }
}
