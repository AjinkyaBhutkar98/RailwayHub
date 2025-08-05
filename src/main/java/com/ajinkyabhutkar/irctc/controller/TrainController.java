package com.ajinkyabhutkar.irctc.controller;


import com.ajinkyabhutkar.irctc.dto.ErrorResponse;
import com.ajinkyabhutkar.irctc.dto.TrainDTO;
import com.ajinkyabhutkar.irctc.entity.ImageMetaData;
import com.ajinkyabhutkar.irctc.entity.Train;
import com.ajinkyabhutkar.irctc.service.FileUploadService;
import com.ajinkyabhutkar.irctc.service.TrainService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

//@Controller
@RestController
// restcontroller is controller + responsebody
// whenever we wnat to return data we used it
@RequestMapping("/trains")
public class TrainController {


    @Autowired
    TrainService trainService;

    @Autowired
    FileUploadService fileUploadService;


    // get all train
//    @RequestMapping(value = "/",method = RequestMethod.GET)
    @GetMapping
    public ResponseEntity<List<TrainDTO>> getAll(){
        System.out.println("Get all trains called!");

    return new ResponseEntity<>(this.trainService.allTrains(),HttpStatus.OK);

    }

    @PostMapping("/photo")
    public ImageMetaData uploadTrainImage(@RequestParam("file") MultipartFile multipartFile) throws IOException {

        ImageMetaData imageMetaData=fileUploadService.upload(multipartFile);

        return imageMetaData;

    }


    @GetMapping("/{trainNo}")
    public ResponseEntity<TrainDTO> getTrain(@PathVariable("trainNo") String trainNo){

        return new ResponseEntity<TrainDTO>(this.trainService.getTrain(trainNo),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TrainDTO> add(@Valid @RequestBody TrainDTO train){

        return new ResponseEntity<>(trainService.addTrain(train), HttpStatus.CREATED);
    }

    @DeleteMapping("/{trainNo}")
    public void delete(@PathVariable String trainNo){

        this.trainService.delete(trainNo);

    }


    //Excetion handling controller level

//    @ExceptionHandler(NoSuchElementException.class)
//    public ErrorResponse handleNoSuchElementException(NoSuchElementException exception){
//
//        ErrorResponse response=new ErrorResponse("Train Not found"+exception.getMessage(),"404",false);
//
//        return response;
//    }

//    @RequestMapping("/all")
//    @ResponseBody
//    public List<Train> getTrains(){
//
//        Train baramatiTrain=new Train();
//        baramatiTrain.setTrainNo("1");
//        baramatiTrain.setName("Baramati to pune");
//        baramatiTrain.setCoaches(15);
//        System.out.println(baramatiTrain);
//
//        Train puneTrain=new Train();
//        puneTrain.setTrainNo("2");
//        puneTrain.setName("Pune to Mumbai");
//        puneTrain.setCoaches(26);
//        System.out.println(puneTrain);
//
//        List<Train> allTrains=new ArrayList<>();
//
//        allTrains.add(baramatiTrain);
//        allTrains.add(puneTrain);
//
//        return allTrains;
//    }
}
