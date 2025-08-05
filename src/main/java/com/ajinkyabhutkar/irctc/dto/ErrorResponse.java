package com.ajinkyabhutkar.irctc.dto;

// records are immutable classes
public record ErrorResponse(String messege,String code,boolean success) {


}
