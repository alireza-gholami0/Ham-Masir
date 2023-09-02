package com.example.foodapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FoodDTO(@NotNull @NotBlank Long foodId, @NotNull @NotBlank String foodName, @NotNull @NotBlank String description,
                        @NotNull @NotBlank Double price) {}
