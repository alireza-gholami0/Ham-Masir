package com.example.foodapi.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
@Builder
public record LoginRequest(@NotNull @NotBlank String email, @NotNull @NotBlank String password) {}
