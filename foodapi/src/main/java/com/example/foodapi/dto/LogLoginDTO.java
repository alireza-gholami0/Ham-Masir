package com.example.foodapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;


@Builder
public record LogLoginDTO(@NotNull @NotBlank String email, @NotBlank @NotBlank LocalDateTime dateTime) {
}
