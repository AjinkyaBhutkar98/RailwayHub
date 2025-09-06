package com.ajinkyabhutkar.irctc.repo;

import com.ajinkyabhutkar.irctc.entity.Train;
import com.ajinkyabhutkar.irctc.entity.TrainRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainRepo extends JpaRepository<Train,Long> {


//    public Train findByTrainNo(String trainNO);

//    Train deleteByTrainNo(String trainNO);

    List<Train> findByNameContainingIgnoreCase(String name);

    @Query(value = "SELECT tr.train from TrainRoute tr  WHERE tr.station.id= :sourceStationId OR tr.station.id= :destinationStationId")
    List<Train> findTrainBySourceAndDestination(@Param("sourceStationId") Long sourceStationId, @Param("destinationStationId") Long destinationStationId);

}
