package com.example.foodapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderFoodDTO(@NotNull @NotBlank Long foodId, @NotNull @NotBlank Integer quantity) {
}
