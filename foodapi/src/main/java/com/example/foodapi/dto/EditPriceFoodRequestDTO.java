package com.example.foodapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EditPriceFoodRequestDTO(@NotNull @NotBlank long restaurantId, @NotNull @NotBlank long foodId,
                                      @NotNull @NotBlank double price) { }
