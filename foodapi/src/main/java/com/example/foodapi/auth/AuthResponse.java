package com.example.foodapi.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AuthResponse(@NotNull @NotBlank String token) { }
