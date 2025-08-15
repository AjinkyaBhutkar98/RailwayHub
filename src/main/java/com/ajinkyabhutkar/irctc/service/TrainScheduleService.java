package com.ajinkyabhutkar.irctc.service;

import com.ajinkyabhutkar.irctc.dto.TrainScheduleDto;

import java.util.List;

public interface TrainScheduleService {

    TrainScheduleDto createSchedule(TrainScheduleDto trainScheduleDto);

    List<TrainScheduleDto> getTrainScheduleDtoById(Long trainId);

    void deleteSchedule(Long trainScheduleId);

    TrainScheduleDto updateTrainScheduleDto(Long trainScheduleId,TrainScheduleDto trainScheduleDto);

}
