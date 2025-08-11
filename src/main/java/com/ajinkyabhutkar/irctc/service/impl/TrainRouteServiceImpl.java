package com.ajinkyabhutkar.irctc.service.impl;


import com.ajinkyabhutkar.irctc.dto.TrainRouteDto;
import com.ajinkyabhutkar.irctc.entity.Station;
import com.ajinkyabhutkar.irctc.entity.Train;
import com.ajinkyabhutkar.irctc.entity.TrainRoute;
import com.ajinkyabhutkar.irctc.repo.StationRepo;
import com.ajinkyabhutkar.irctc.repo.TrainRepo;
import com.ajinkyabhutkar.irctc.repo.TrainRouteRepo;
import com.ajinkyabhutkar.irctc.service.TrainRouteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainRouteServiceImpl implements TrainRouteService {

    private TrainRepo trainRepo;

    private StationRepo stationRepo;

    private ModelMapper modelMapper;

    private TrainRouteRepo trainRouteRepo;

    @Autowired
    public TrainRouteServiceImpl(TrainRepo trainRepo, StationRepo stationRepo, ModelMapper modelMapper, TrainRouteRepo trainRouteRepo) {
        this.trainRepo = trainRepo;
        this.stationRepo = stationRepo;
        this.modelMapper = modelMapper;
        this.trainRouteRepo = trainRouteRepo;
    }

    @Override
    public TrainRouteDto addTrainRoute(TrainRouteDto routeDto) {

        Train train = trainRepo.findById(routeDto.getTrain().getId()).orElseThrow(() -> new RuntimeException("No Train Found"));
        Station station = stationRepo.findById(routeDto.getStation().getId()).orElseThrow(() -> new RuntimeException("Station Not Found!!"));

        //converted whole routeDto object to trainroute object
        TrainRoute newTrainRoute = modelMapper.map(routeDto, TrainRoute.class);
        newTrainRoute.setTrain(train);
        newTrainRoute.setStation(station);

        TrainRouteDto newTrainRouteDto = modelMapper.map(newTrainRoute, TrainRouteDto.class);
        trainRouteRepo.save(newTrainRoute);
        return newTrainRouteDto;
    }


    @Override
    public TrainRouteDto getTrainRoute(Long id) {
        return null;
    }

    @Override
    public List<TrainRouteDto> getAllTrainRoutes() {

        return trainRouteRepo.findAll().stream().map(t->modelMapper.map(t, TrainRouteDto.class)).toList();
    }

    @Override
    public List<TrainRouteDto> getRoutesByTrain(Long id) {

        Train train = trainRepo.findById(id).orElseThrow(() -> new RuntimeException("No Train Found"));

        List<TrainRoute> routesByTrain = trainRouteRepo.findByTrain(train);

        return routesByTrain.stream().map(trainRoute -> modelMapper.map(trainRoute, TrainRouteDto.class)).toList();
    }


    @Override
    public TrainRouteDto updateTrainRoute(Long trainId, TrainRouteDto routeDto) {
        TrainRoute existingTrainRoute=trainRouteRepo.findById(trainId).orElseThrow(()->new RuntimeException("Train route not found"));

        TrainRoute updatedTrainRoute=modelMapper.map(routeDto,TrainRoute.class);

        Station sourceStation=stationRepo.findById(routeDto.getStation().getId()).orElseThrow(()->new RuntimeException("source station not found"));

        updatedTrainRoute.setStation(sourceStation);
        updatedTrainRoute.setTrain(existingTrainRoute.getTrain());
        updatedTrainRoute.setArrivalTime(routeDto.getArrivalTime());
        updatedTrainRoute.setHaltTime(routeDto.getHaltTime());
        updatedTrainRoute.setDepartureTime(routeDto.getDepartureTime());
        updatedTrainRoute.setStationOrder(routeDto.getStationOrder());
        updatedTrainRoute.setDistanceFromSource(routeDto.getDistanceFromSource());

        TrainRoute updatedRoute=trainRouteRepo.save(updatedTrainRoute);

        return modelMapper.map(updatedRoute, TrainRouteDto.class);
    }

    @Override
    public void deleteRoute(Long id) {

        TrainRoute trainRoute=trainRouteRepo.findById(id).orElseThrow(()->new RuntimeException("Train Route Not Found!!!"));
        trainRouteRepo.delete(trainRoute);

    }
}
