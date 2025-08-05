package com.ajinkyabhutkar.irctc.repo;

import com.ajinkyabhutkar.irctc.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepo extends JpaRepository<Train,String> {


    public Train findByTrainNo(String trainNO);

    Train deleteByTrainNo(String trainNO);
}
