package com.example.foodapi.auth;

import com.example.foodapi.domain.RoleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record SignupRequest(@NotNull @NotBlank String firstname, @NotNull @NotBlank String lastname,
                            @NotNull @NotBlank String address,
                            @NotNull @NotBlank String email, @NotNull @NotBlank String password,
                            @NotNull @NotBlank RoleType role) { }
