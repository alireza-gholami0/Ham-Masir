package com.example.foodapi.dto.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderDTO(@NotNull @NotBlank long id, @NotNull @NotBlank List<OrderFoodDTO> orderFoods, @NotNull @NotBlank double totalPrice) {
}
