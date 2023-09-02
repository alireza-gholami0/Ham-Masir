package com.example.foodapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RestaurantDTO(@NotNull @NotBlank long restaurantId, @NotNull @NotBlank String name, @NotNull @NotBlank String city,
                            @NotNull @NotBlank String address, @NotNull @NotBlank String phoneNumber) {
}
