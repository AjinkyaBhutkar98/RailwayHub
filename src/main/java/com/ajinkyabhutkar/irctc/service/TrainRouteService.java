package com.ajinkyabhutkar.irctc.service;

import com.ajinkyabhutkar.irctc.dto.TrainRouteDto;
import com.ajinkyabhutkar.irctc.entity.TrainRoute;

import java.util.List;

public interface TrainRouteService {

    TrainRouteDto addTrainRoute(TrainRouteDto routeDto);

    TrainRouteDto getTrainRoute(Long id);

    List<TrainRouteDto> getAllTrainRoutes();

    List<TrainRouteDto> getRoutesByTrain(Long id);

    TrainRouteDto updateTrainRoute(Long trainId,TrainRouteDto routeDto);

    void deleteRoute(Long id);
}
