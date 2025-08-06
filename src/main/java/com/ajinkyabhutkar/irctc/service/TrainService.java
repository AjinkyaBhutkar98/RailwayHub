package com.ajinkyabhutkar.irctc.service;

import com.ajinkyabhutkar.irctc.dto.TrainDTO;
import com.ajinkyabhutkar.irctc.entity.Train;
import com.ajinkyabhutkar.irctc.exceptions.TrainNotFoundException;
import com.ajinkyabhutkar.irctc.repo.TrainRepo;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainService {

    @Autowired
    TrainRepo trainRepo;


    @Autowired
    private ModelMapper modelMapper;

    List<Train> trainList=new ArrayList<>();

    public TrainService(){

    }

    public TrainDTO addTrain(TrainDTO trainDTO){

        Train newTrain=new Train();

//        newTrain.setTrainNo(trainDTO.getTrainNo());
//        newTrain.setRouteName(trainDTO.getRouteName());
        newTrain.setName(trainDTO.getName());

        //using ModelMapper
//        Train train=modelMapper.map(trainDTO,Train.class);

        Train train=trainRepo.save(newTrain);

        TrainDTO trainDTO1=new TrainDTO();

//        TrainDTO trainDTO1=modelMapper.map(train, TrainDTO.class);
        trainDTO1.setName(train.getName());
//        trainDTO1.setTrainNo(train.getTrainNo());
//        trainDTO1.setRouteName(train.getRouteName());

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
