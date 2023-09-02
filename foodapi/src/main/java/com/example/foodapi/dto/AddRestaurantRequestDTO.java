package com.example.foodapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddRestaurantRequestDTO(@NotNull @NotBlank String name, @NotNull @NotBlank String address, @NotNull @NotBlank String city,
                                      @NotNull @NotBlank String phone_number) {
}
