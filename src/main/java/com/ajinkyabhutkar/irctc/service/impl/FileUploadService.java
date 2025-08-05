package com.ajinkyabhutkar.irctc.service.impl;


import com.ajinkyabhutkar.irctc.entity.ImageMetaData;
import com.ajinkyabhutkar.irctc.repo.FileUploadRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class FileUploadService implements com.ajinkyabhutkar.irctc.service.FileUploadService {


    @Value("${file.upload.folder}")
    private String folder;

    @Autowired
    FileUploadRepo fileUploadRepo;

    @Override
    public ImageMetaData upload(MultipartFile multipartFile) throws IOException {
        //process the file
        String ogFileName=multipartFile.getOriginalFilename();

        InputStream inputStream=multipartFile.getInputStream();

//        String folder="uploads/";
        String filePath=folder+ UUID.randomUUID()+ogFileName;

        //create folder if not exist
        if(!Files.exists(Paths.get(folder))){

            System.out.println("Creating folder");
            Files.createDirectories(Paths.get(folder));
        }

        //upload files
        Files.copy(multipartFile.getInputStream(),Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);


        ImageMetaData imageMetaData=new ImageMetaData();
        imageMetaData.setFileId(UUID.randomUUID().toString());
        imageMetaData.setFileName(ogFileName);
        imageMetaData.setFileSize(multipartFile.getSize());
        imageMetaData.setContentType(multipartFile.getContentType());
        imageMetaData.setCreateTime(LocalDateTime.now());

        fileUploadRepo.save(imageMetaData);

        return imageMetaData;
    }
}
