package com.example.foodapi.mapper;

import com.example.foodapi.domain.Food;
import com.example.foodapi.dto.FoodDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface MapStructFood {
    @Mapping(source = "id", target = "foodId")
    @Mapping(source = "name", target = "foodName")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    FoodDTO FOOD_DTO(Food food);

    @Mapping(source = "foodId", target = "id")
    @Mapping(source = "foodName", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    Food FOOD(FoodDTO request);

    List<FoodDTO> FOOD_DTO_LIST(List<Food> foods);
}

