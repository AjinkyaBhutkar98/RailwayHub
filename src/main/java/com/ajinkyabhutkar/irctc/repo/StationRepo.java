package com.ajinkyabhutkar.irctc.repo;

import com.ajinkyabhutkar.irctc.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepo extends JpaRepository<Station,Long> {


}
