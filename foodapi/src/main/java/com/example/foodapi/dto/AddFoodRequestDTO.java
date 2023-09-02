package com.example.foodapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddFoodRequestDTO(@NotNull @NotBlank long restaurantId, @NotNull @NotBlank String foodName,
                                @NotNull @NotBlank String description, @NotNull @NotBlank double price) {
}
