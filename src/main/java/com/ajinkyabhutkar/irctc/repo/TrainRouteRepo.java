package com.ajinkyabhutkar.irctc.repo;

import com.ajinkyabhutkar.irctc.entity.Train;
import com.ajinkyabhutkar.irctc.entity.TrainRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainRouteRepo extends JpaRepository<TrainRoute,Long> {

    public List<TrainRoute> findByTrain(Train train);
}
