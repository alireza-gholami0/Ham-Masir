package com.example.foodapi.auth;

import lombok.Builder;
import lombok.Data;
@Builder
public record LoginRequest(String email, String password) {}
