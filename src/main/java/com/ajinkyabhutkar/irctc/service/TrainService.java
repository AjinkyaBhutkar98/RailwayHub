package com.ajinkyabhutkar.irctc.service;

import com.ajinkyabhutkar.irctc.dto.PagedResponse;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


public interface TrainService {

    public TrainDTO addTrain(TrainDTO trainDTO);

    public List<TrainDTO> allTrains();

    public TrainDTO getTrain(Long trainNo);

    public TrainDTO delete(Long trainNo);

    public PagedResponse<TrainDTO> getAllTrains(int page, int size, String sortBy, String sortDir);

    public TrainDTO updateTrain(Long id,TrainDTO trainDTO);

    public void deleteTrain(Long id);

    public List<TrainDTO> searchByName(String name);

}
