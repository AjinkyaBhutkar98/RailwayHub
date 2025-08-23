package com.ajinkyabhutkar.irctc.repo;

import com.ajinkyabhutkar.irctc.entity.TrainSchedule;
import com.ajinkyabhutkar.irctc.entity.TrainSeats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainSeatRepo extends JpaRepository<TrainSeats,Long> {

    @Query("SELECT ts FROM TrainSeats ts WHERE ts.trainSchedule.id = ?1")
    List<TrainSeats> findByTrainScheduleId(Long trainScheduleId);
}
