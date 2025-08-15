package com.ajinkyabhutkar.irctc.service.impl;

import com.ajinkyabhutkar.irctc.dto.TrainScheduleDto;
import com.ajinkyabhutkar.irctc.entity.Train;
import com.ajinkyabhutkar.irctc.entity.TrainSchedule;
import com.ajinkyabhutkar.irctc.exceptions.TrainNotFoundException;
import com.ajinkyabhutkar.irctc.repo.TrainRepo;
import com.ajinkyabhutkar.irctc.repo.TrainScheduleRepo;
import com.ajinkyabhutkar.irctc.service.TrainScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainScheduleServiceImpl implements TrainScheduleService {

    private TrainRepo trainRepo;
    private ModelMapper modelMapper;
    private TrainScheduleRepo trainScheduleRepo;

    @Autowired
    public TrainScheduleServiceImpl(TrainRepo trainRepo, ModelMapper modelMapper, TrainScheduleRepo trainScheduleRepo) {
        this.trainRepo = trainRepo;
        this.modelMapper = modelMapper;
        this.trainScheduleRepo = trainScheduleRepo;
    }

    // add train schedule
    @Override
    public TrainScheduleDto createSchedule(TrainScheduleDto trainScheduleDto) {

        Train train=trainRepo.findById(trainScheduleDto.getTrainId()).orElseThrow(()->new TrainNotFoundException("Train not found with id"+trainScheduleDto.getTrainId()));

        TrainSchedule trainSchedule=modelMapper.map(trainScheduleDto,TrainSchedule.class);
        trainSchedule.setTrain(train);
        TrainSchedule savedTrainSchedule=trainScheduleRepo.save(trainSchedule);
        return modelMapper.map(savedTrainSchedule,TrainScheduleDto.class);
    }

    // find trainschedule by train id
    @Override
    public List<TrainScheduleDto> getTrainScheduleDtoById(Long trainId) {

        List<TrainSchedule> trainSchedules=trainScheduleRepo.findByTrainId(trainId);

        return trainSchedules.stream().map(trainSchedule -> (modelMapper.map(trainSchedule,TrainScheduleDto.class))).toList();
    }

    //delete Train schedule
    @Override
    public void deleteSchedule(Long trainScheduleId) {

        TrainSchedule trainSchedule=trainScheduleRepo.findById(trainScheduleId).orElseThrow(()->new RuntimeException("Train schedule not found!!!"));
    }

    //update Train Schedule
    @Override
    public TrainScheduleDto updateTrainScheduleDto(Long trainScheduleId, TrainScheduleDto trainScheduleDto) {

        TrainSchedule trainSchedule=trainScheduleRepo.findById(trainScheduleId).orElseThrow(()->new RuntimeException("Train schedule not found!!!"));
        Train train=trainRepo.findById(trainScheduleDto.getTrainId()).orElseThrow(()->new TrainNotFoundException("Train not found with id"+trainScheduleDto.getTrainId()));
        TrainSchedule updatedTrainSchedule=modelMapper.map(trainScheduleDto,TrainSchedule.class);
        updatedTrainSchedule.setTrain(train);
        TrainSchedule trainSchedule1=trainScheduleRepo.save(updatedTrainSchedule);
        return modelMapper.map(trainSchedule1,TrainScheduleDto.class);
    }
}
