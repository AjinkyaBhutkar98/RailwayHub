package com.ajinkyabhutkar.irctc.repo;

import com.ajinkyabhutkar.irctc.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainRepo extends JpaRepository<Train,Long> {


//    public Train findByTrainNo(String trainNO);

//    Train deleteByTrainNo(String trainNO);

    List<Train> findByNameContainingIgnoreCase(String name);
}
