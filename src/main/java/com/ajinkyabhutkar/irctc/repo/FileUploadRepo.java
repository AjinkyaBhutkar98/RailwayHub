package com.ajinkyabhutkar.irctc.repo;

import com.ajinkyabhutkar.irctc.entity.ImageMetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadRepo extends JpaRepository<ImageMetaData,Integer> {
}
