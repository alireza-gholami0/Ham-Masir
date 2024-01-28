package com.example.foodapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddFoodDTO(@NotNull Long restaurantId, @NotNull @NotBlank String foodName,
                         @NotNull @NotBlank String description, @NotNull Double price) {
}
