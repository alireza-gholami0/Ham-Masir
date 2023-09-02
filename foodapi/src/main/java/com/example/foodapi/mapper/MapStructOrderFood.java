package com.example.foodapi.mapper;

import com.example.foodapi.domain.OrderFood;
import com.example.foodapi.domain.Restaurant;
import com.example.foodapi.dto.OrderFoodDTO;
import com.example.foodapi.dto.RestaurantDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapStructOrderFood {
    @Mapping(source = "foodId",target = "id")
    OrderFood ORDER_FOOD (OrderFoodDTO orderFoodDTO);
}
