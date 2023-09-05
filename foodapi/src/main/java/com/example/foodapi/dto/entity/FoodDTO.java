package com.example.foodapi.dto.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FoodDTO(@NotNull @NotBlank Long id, @NotNull @NotBlank String name, @NotNull @NotBlank String description,
                        @NotNull @NotBlank Double price) {}
