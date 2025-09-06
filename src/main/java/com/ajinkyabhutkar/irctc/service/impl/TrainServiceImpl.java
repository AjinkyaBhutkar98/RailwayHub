package com.ajinkyabhutkar.irctc.service.impl;

import com.ajinkyabhutkar.irctc.dto.*;
import com.ajinkyabhutkar.irctc.entity.*;
import com.ajinkyabhutkar.irctc.repo.TrainRepo;
import com.ajinkyabhutkar.irctc.repo.TrainScheduleRepo;
import com.ajinkyabhutkar.irctc.service.StationService;
import com.ajinkyabhutkar.irctc.service.TrainService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TrainServiceImpl implements TrainService {

    private TrainRepo trainRepo;

    private StationService stationService;


    private TrainScheduleRepo trainScheduleRepo;

    private ModelMapper modelMapper;

    private List<Train> trainList = new ArrayList<>();

    @Autowired
    public TrainServiceImpl(TrainRepo trainRepo, StationService stationService, TrainScheduleRepo trainScheduleRepo, ModelMapper modelMapper, List<Train> trainList) {
        this.trainRepo = trainRepo;
        this.stationService = stationService;
        this.trainScheduleRepo = trainScheduleRepo;
        this.modelMapper = modelMapper;
        this.trainList = trainList;
    }

    public TrainServiceImpl() {

    }

    public TrainDTO addTrain(TrainDTO trainDTO) {

        Train newTrain = new Train();

        StationDto sourceStationDto = stationService.getStationById(trainDTO.getSourceStation().getId());
        StationDto destinationStationDto = stationService.getStationById(trainDTO.getDestinationStation().getId());
        Station sourcestation = modelMapper.map(sourceStationDto, Station.class);
        Station destinationstation = modelMapper.map(destinationStationDto, Station.class);
        System.out.println(sourceStationDto.getId());

        newTrain.setName(trainDTO.getName());
        newTrain.setSourceStation(sourcestation);
        newTrain.setDestinationStation(destinationstation);
        newTrain.setNumber(trainDTO.getNumber());
        newTrain.setTotalTravelDistance(trainDTO.getTotalTravelDistance());


        Train train = trainRepo.save(newTrain);

        TrainDTO trainDTO1 = modelMapper.map(train, TrainDTO.class);


        return trainDTO1;
    }

    public List<TrainDTO> allTrains() {
        List<Train> allTrains = trainRepo.findAll();

        List<TrainDTO> dtoTrains = allTrains.stream()
                .map(train -> modelMapper.map(train, TrainDTO.class))
                .toList();

        return dtoTrains;
    }


    public TrainDTO getTrain(Long trainNo) {

        Train train = trainRepo.findById(trainNo).orElseThrow(() -> new RuntimeException("Train not found!!"));

        return modelMapper.map(train, TrainDTO.class);

    }

    public TrainDTO delete(Long trainNo) {

        Train train = trainRepo.findById(trainNo).orElseThrow(() -> new RuntimeException("Train not found!!"));

        trainRepo.delete(train);

        return modelMapper.map(train, TrainDTO.class);
    }

    public PagedResponse<TrainDTO> getAllTrains(
            int page, int size, String sortBy, String sortDir

    ) {
        Sort sort = sortDir.trim().equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        // pass page size(from method arguments) sort

        Pageable pageable = PageRequest.of(page, size, sort);
        //it will content data

        Page<Train> trainPage = trainRepo.findAll(pageable);

        Page<TrainDTO> page2 = trainPage.map(train -> modelMapper.map(train, TrainDTO.class));

        return PagedResponse.fromPage(page2);

    }

    public TrainDTO updateTrain(Long id, TrainDTO trainDTO) {

        Train train = trainRepo.findById(id).orElseThrow(() -> new RuntimeException("Train not found!!"));
        train.setName(trainDTO.getName());
        train.setNumber(trainDTO.getNumber());
        train.setTotalTravelDistance(trainDTO.getTotalTravelDistance());
        train.setSourceStation(train.getSourceStation());
        train.setDestinationStation(train.getDestinationStation());

        Train savedTrain = trainRepo.save(train);

        return modelMapper.map(savedTrain, TrainDTO.class);

    }

    public void deleteTrain(Long id) {

        Train train = trainRepo.findById(id).orElseThrow(() -> new RuntimeException("Train not found!!"));

        trainRepo.delete(train);

        System.out.println("Train deleted successfully");
    }

    @Override
    public List<TrainDTO> searchByName(String name) {

        List<Train> namedTrains = trainRepo.findByNameContainingIgnoreCase(name);

//        List<TrainDTO> trainDTOS=modelMapper.map(namedTrains->namedTrains)
        return namedTrains.stream().map(train -> modelMapper.map(train, TrainDTO.class)).toList();
    }


    @Override
    public List<AvailableTrainResponse> userTrainSearch(UserTrainSearchRequest userTrainSearchRequest) {

        List<Train> matchedTrains = this.trainRepo.findTrainBySourceAndDestination(userTrainSearchRequest.getSourceStationId(), userTrainSearchRequest.getDestinationStationId());

        //first we find all available trains for that rundate
        List<Train> validTrains = new ArrayList<>();

        for (Train train : matchedTrains) {

            Integer sourceStationOrder = null;
            Integer destinationStationOrder = null;

            for (TrainRoute trainRoute : train.getRoutes()) {

                if (trainRoute.getStation().getId().equals(userTrainSearchRequest.getSourceStationId())) {
                    sourceStationOrder = trainRoute.getStationOrder();
                } else if (trainRoute.getStation().getId().equals(userTrainSearchRequest.getDestinationStationId())) {
                    destinationStationOrder = trainRoute.getStationOrder();
                }

            }

            //check if available for that day
            boolean isTrainAvailable = train.getSchedules().stream().anyMatch(schedule -> schedule.getRunDate().equals(userTrainSearchRequest.getJourneyDate()));

            if (sourceStationOrder != null && destinationStationOrder != null && sourceStationOrder < destinationStationOrder) {

                validTrains.add(train);
            }

        }

        //secondly we get all available schedules for that train
        List<AvailableTrainResponse> availableTrainResponses = new ArrayList<>();

        for (Train t : validTrains) {

            TrainSchedule trainSchedule = t.getSchedules().stream().filter(trainSchedule1 -> trainSchedule1.getRunDate().equals(userTrainSearchRequest.getJourneyDate())).findFirst().orElse(null);

            Map<CoachType, Integer> seatsMap = new HashMap<>();
            Map<CoachType, Double> priceMap = new HashMap<>();

            TrainRoute sourceRoute = t.getRoutes().stream().filter(r -> r.getStation().getId().equals(userTrainSearchRequest.getSourceStationId())).findFirst().orElse(null);
//            TrainRoute destinationRoute = t.getRoutes().stream().filter(r -> r.getStation().getId().equals(userTrainSearchRequest.getDestinationStationId())).findFirst().orElse(null);
//
//            if (sourceRoute != null || destinationRoute != null) {
//                continue;
//            }

            for (TrainSeats seats : trainSchedule.getTrainSeats()) {
                seatsMap.merge(seats.getCoachType(), seats.getAvailableSeats(), Integer::sum);
                priceMap.putIfAbsent(seats.getCoachType(), seats.getPrice());
            }


            AvailableTrainResponse availableTrainResponse=AvailableTrainResponse.builder()
                    .trainId(t.getId())
                    .trainNumber(t.getNumber())
                    .trainName(t.getName())
                    .departureTime(sourceRoute.getDepartureTime())
                    .arrivalTime(sourceRoute.getArrivalTime())
                    .seatsAvailable(seatsMap)
                    .priceByCoach(priceMap)
                    .seatsAvailable(seatsMap)
                    .priceByCoach(priceMap)
                    .build();

            availableTrainResponses.add(availableTrainResponse);
        }

        return availableTrainResponses;

    }


}
