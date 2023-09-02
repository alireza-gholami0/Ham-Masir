package com.example.foodapi.dto;

import com.example.foodapi.domain.RoleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDTO(@NotNull @NotBlank long id, @NotNull @NotBlank String first_name,
                      @NotNull @NotBlank String last_name, @NotNull @NotBlank String address,
                      @NotNull @NotBlank String email, @NotNull @NotBlank RoleType role) {
}
