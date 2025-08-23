package com.ajinkyabhutkar.irctc.dto;


public record LoginRequest(
        String username,
        String password
) {
}