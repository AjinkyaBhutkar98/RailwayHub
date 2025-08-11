package com.ajinkyabhutkar.irctc.repo;

import com.ajinkyabhutkar.irctc.entity.TrainRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRouteRepo extends JpaRepository<TrainRoute,Long> {
}
