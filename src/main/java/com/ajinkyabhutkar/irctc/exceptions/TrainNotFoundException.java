package com.ajinkyabhutkar.irctc.exceptions;

public class TrainNotFoundException extends RuntimeException{

    public TrainNotFoundException(){
        super("Train not found exception default constructor");
    }

    public TrainNotFoundException(String messege){

        super(messege);
    }
}
