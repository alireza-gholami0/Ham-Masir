package com.example.foodapi.dto.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.implementation.bind.annotation.Default;

//public record RestaurantDTO(@NotNull @NotBlank long id, @NotNull @NotBlank String name, @NotNull @NotBlank String city,
//                            @NotNull @NotBlank String address, @NotNull @NotBlank String phoneNumber) {
//}
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {
    @NotNull @NotBlank
    private long id;
    @NotNull @NotBlank
    private String name;
    @NotNull @NotBlank
    private String city;
    @NotNull @NotBlank
    private String address;
    @NotNull @NotBlank
    private String phoneNumber;
}