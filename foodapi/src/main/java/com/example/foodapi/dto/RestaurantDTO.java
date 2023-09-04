package com.example.foodapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
public record RestaurantDTO(@NotNull @NotBlank long id, @NotNull @NotBlank String name, @NotNull @NotBlank String city,
                            @NotNull @NotBlank String address, @NotNull @NotBlank String phoneNumber) {
}
//@AllArgsConstructor
//@RequiredArgsConstructor
//@Setter
//@Getter
//public class RestaurantDTO {
//    private Long id;
//    private String name;
//    private String city;
//    private String address;
//    private String phoneNumber;
//}