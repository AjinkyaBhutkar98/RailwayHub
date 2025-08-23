package com.ajinkyabhutkar.irctc.service;

import com.ajinkyabhutkar.irctc.dto.TrainSeatDto;
import com.ajinkyabhutkar.irctc.entity.TrainSeats;

import java.util.List;

public interface TrainSeatService {

    TrainSeatDto createSeatInfo(TrainSeatDto trainSeatDto);

    List<TrainSeatDto> getSeatInfoByTrainScheduleId(Long scheduleId);

    void deleteSeatInfo(Long seatId);

    TrainSeatDto updateSeatsinfo(Long seatId,TrainSeatDto trainSeatDto);

    public List<Integer> bookSeat(int seatToBook, Long seatId);
}
