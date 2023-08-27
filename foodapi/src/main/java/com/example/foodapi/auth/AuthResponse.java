package com.example.foodapi.auth;

import lombok.Builder;

@Builder
public record AuthResponse(String token) { }
