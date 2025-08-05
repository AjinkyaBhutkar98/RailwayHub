package com.ajinkyabhutkar.irctc.service;

import com.ajinkyabhutkar.irctc.entity.ImageMetaData;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface FileUploadService {

    public ImageMetaData upload(MultipartFile file) throws IOException;
}
