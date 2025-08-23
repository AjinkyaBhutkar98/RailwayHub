package com.ajinkyabhutkar.irctc.dto;

public record JwtResponse(
        String token,
        String username
) {
}
