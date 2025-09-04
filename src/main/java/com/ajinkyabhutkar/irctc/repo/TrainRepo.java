package com.ajinkyabhutkar.irctc.repo;

import com.ajinkyabhutkar.irctc.entity.Train;
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

    @Query("""
            SELECT tr.train from TrainRoute
            WHERE tr.station.id= :sourceStationId or tr.station.id= :destinationId
            GROUP BY tr.train.id
            HAVING SUM(CASE WHEN tr.station.id=:sourceStationId THEN 1 ELSE 0 END)>0 AND SUM(CASE WHEN tr.station.id=:destinationId THEN 1 ELSE 0 END)>0 AND (MIN(CASE WHEN tr.station.id=:sourceStationId THEN tr.stationOrder ELSE 999999 END) < MIN(CASE WHEN tr.station.id=:destinationStationId THEN tr.stationOrder ELSE 999999 END))
            """)
    List<Train> findTrainBySourceAndDestinationInOrder(@Param("sourceStationId") Long sourceStationId,@Param("destinationStationId") Long destinationStationId);
}
