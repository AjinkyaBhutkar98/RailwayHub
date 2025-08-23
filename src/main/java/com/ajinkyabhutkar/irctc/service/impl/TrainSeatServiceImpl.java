package com.ajinkyabhutkar.irctc.service.impl;

import com.ajinkyabhutkar.irctc.dto.TrainSeatDto;
import com.ajinkyabhutkar.irctc.entity.TrainSchedule;
import com.ajinkyabhutkar.irctc.entity.TrainSeats;
import com.ajinkyabhutkar.irctc.repo.TrainScheduleRepo;
import com.ajinkyabhutkar.irctc.repo.TrainSeatRepo;
import com.ajinkyabhutkar.irctc.service.TrainScheduleService;
import com.ajinkyabhutkar.irctc.service.TrainSeatService;
import lombok.Synchronized;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainSeatServiceImpl implements TrainSeatService {

    private TrainScheduleRepo trainScheduleRepo;

    private TrainSeatRepo trainSeatRepo;

    private ModelMapper modelMapper;


    @Autowired
    public TrainSeatServiceImpl(TrainScheduleRepo trainScheduleRepo, TrainSeatRepo trainSeatRepo, ModelMapper modelMapper) {
        this.trainScheduleRepo = trainScheduleRepo;
        this.trainSeatRepo = trainSeatRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public TrainSeatDto createSeatInfo(TrainSeatDto trainSeatDto) {
        TrainSchedule trainSchedule=trainScheduleRepo.findById(trainSeatDto.getTrainScheduleId()).orElseThrow(()->new RuntimeException("Train Schedule Not found !!"));

        TrainSeats trainSeats=modelMapper.map(trainSeatDto,TrainSeats.class);
        trainSeats.setTrainSchedule(trainSchedule);

        return modelMapper.map(trainSeatRepo.save(trainSeats),TrainSeatDto.class);
    }

    @Override
    public List<TrainSeatDto> getSeatInfoByTrainScheduleId(Long scheduleId) {

        List<TrainSeats> trainSeats=trainSeatRepo.findByTrainScheduleId(scheduleId);

        return trainSeats.stream().map(trainSeat->modelMapper.map(trainSeat,TrainSeatDto.class)).toList();
    }

    @Override
    public void deleteSeatInfo(Long seatId) {

        TrainSeats trainSeats=trainSeatRepo.findById(seatId).orElseThrow(()->new RuntimeException("Train seat not found with id"+seatId));
        trainSeatRepo.delete(trainSeats);
    }

    @Override
    public TrainSeatDto updateSeatsinfo(Long seatId, TrainSeatDto trainSeatDto) {

        TrainSeats trainSeats = trainSeatRepo.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Train seat not found with id " + seatId));

        TrainSchedule trainSchedule = trainScheduleRepo.findById(trainSeatDto.getTrainScheduleId())
                .orElseThrow(() -> new RuntimeException("Train Schedule Not found !!"));

        trainSeats.setTrainSchedule(trainSchedule);
        trainSeats.setCoachType(trainSeatDto.getCoachType());
        trainSeats.setTotalSeats(trainSeatDto.getTotalSeats());
        trainSeats.setAvailableSeats(trainSeatDto.getAvailableSeats());
        trainSeats.setPrice(trainSeatDto.getPrice());
        trainSeats.setSeatNumberToAssign(trainSeatDto.getSeatNumberToAssign());

        return modelMapper.map(trainSeatRepo.save(trainSeats), TrainSeatDto.class);
    }

    @Synchronized
    public List<Integer> bookSeat(int seatToBook, Long seatId)
    {
        TrainSeats trainSeat = trainSeatRepo.findById(seatId).orElseThrow(() -> new RuntimeException("Train Seat not found with id: " + seatId));
        if (trainSeat.isSeatAvailable(seatToBook)) {
            trainSeat.setAvailableSeats(trainSeat.getAvailableSeats() - seatToBook);
            List<Integer> bookedSeats = new ArrayList<>();
            for (int i = 1; i <= seatToBook; i++) {
                bookedSeats.add(trainSeat.getSeatNumberToAssign());
                trainSeat.setSeatNumberToAssign(trainSeat.getSeatNumberToAssign() + 1);
            }

            trainSeatRepo.save(trainSeat);
            return bookedSeats;
        } else {
            throw new IllegalStateException("No seats available in this coach");
        }
    }
}
