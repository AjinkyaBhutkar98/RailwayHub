package com.ajinkyabhutkar.irctc.repo;

import com.ajinkyabhutkar.irctc.entity.TrainSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainScheduleRepo extends JpaRepository<TrainSchedule,Long> {

    @Query("SELECT ts FROM irctc_train_schedule ts WHERE ts.train.id = ?1")
    List<TrainSchedule> findByTrainId(Long trainId);
}
