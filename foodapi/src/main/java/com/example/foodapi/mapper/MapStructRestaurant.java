package com.example.foodapi.mapper;

import com.example.foodapi.domain.Restaurant;
import com.example.foodapi.dto.RestaurantDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")

public interface MapStructRestaurant {
    @Mapping(source = "id",target = "id")
    RestaurantDTO RESTAURANT_DTO (Restaurant restaurant);
    @Mapping(source = "id",target = "id")
    List<RestaurantDTO> RESTAURANT_DTOS (List<Restaurant> restaurants);
    @Mapping(source = "id",target = "id")
    Restaurant RESTAURANT (RestaurantDTO restaurantDTO);
}
