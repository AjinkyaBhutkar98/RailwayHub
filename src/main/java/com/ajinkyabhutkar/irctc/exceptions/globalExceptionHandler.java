package com.ajinkyabhutkar.irctc.exceptions;

import com.ajinkyabhutkar.irctc.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

//@ControllerAdvice for mvc view pages
@RestControllerAdvice
public class globalExceptionHandler {



    // global exception handling
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException exception){

        ErrorResponse response=new ErrorResponse("Train Not found !! "+exception.getMessage(),"404",false);

        ResponseEntity<ErrorResponse> errorResponseResponseEntity=new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return errorResponseResponseEntity;
    }

    @ExceptionHandler(TrainNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoTrainFoundException(TrainNotFoundException exception){

        ErrorResponse response=new ErrorResponse(exception.getMessage(),"404",false);

        ResponseEntity<ErrorResponse> errorResponseResponseEntity=new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return errorResponseResponseEntity;
    }

    // handle MethodArgumentNotValidException: Validation failed for argument

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){

        Map<String,String> errorResponse=new HashMap<>();

        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach(error ->{
            String errorMesege=error.getDefaultMessage();
            String field=((FieldError)error).getField();

            errorResponse.put(field,errorMesege);
        });


        ResponseEntity<Map<String,String>> error=new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);

        return error;
    }
}
