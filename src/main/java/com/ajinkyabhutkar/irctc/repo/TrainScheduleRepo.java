package com.ajinkyabhutkar.irctc.repo;

import com.ajinkyabhutkar.irctc.entity.TrainSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TrainScheduleRepo extends JpaRepository<TrainSchedule,Long> {

    @Query("SELECT ts FROM TrainSchedule ts WHERE ts.train.id = ?1")
    List<TrainSchedule> findByTrainId(Long trainId);


    @Query("SELECT ts from TrainSchedule ts where ts.train.id= ?1 AND ts.runDate= ?2")
    Optional<TrainSchedule> findByTrainIdAndRunDate(Long trainId, LocalDate runDate);
}
