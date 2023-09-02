package com.example.foodapi.mapper;

import com.example.foodapi.domain.Food;
import com.example.foodapi.domain.Restaurant;
import com.example.foodapi.dto.FoodDTO;
import com.example.foodapi.dto.RestaurantDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")

public interface MapStructRestaurant {
    @Mapping(source = "id",target = "restaurantId")
    RestaurantDTO RESTAURANT_DTO (Restaurant restaurant);
    @Mapping(source = "id",target = "restaurantId")
    List<RestaurantDTO> RESTAURANT_DTOS (List<Restaurant> restaurants);
    @Mapping(source = "restaurantId",target = "id")
    Restaurant RESTAURANT (RestaurantDTO restaurantDTO);
}
