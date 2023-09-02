package com.example.foodapi.service;

import com.example.foodapi.domain.Food;
import com.example.foodapi.domain.Restaurant;
import com.example.foodapi.domain.User;
import com.example.foodapi.dto.FoodDTO;
import com.example.foodapi.mapper.MapStructFood;
import com.example.foodapi.dto.AddFoodRequestDTO;
import com.example.foodapi.dto.EditPriceFoodRequestDTO;
import com.example.foodapi.repository.FoodRepository;
import com.example.foodapi.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;
    private final MapStructFood mapStructFood;
    public FoodDTO addFood(User user, AddFoodRequestDTO request) {
        Restaurant restaurant = restaurantRepository.findById(request.restaurantId()).orElseThrow(()->
                new RuntimeException("Restaurant not found"));
        if (Objects.equals(restaurant.getOwner().getId(), user.getId())){
            Food food = Food.builder()
                    .name(request.foodName())
                    .description(request.description())
                    .price(request.price())
                    .restaurant(restaurant)
                    .build();
            foodRepository.save(food);
            return mapStructFood.FOOD_DTO(food);
        }
        else throw new RuntimeException("You do not have access to this section");

    }

    public FoodDTO editFoodPrice(User user, EditPriceFoodRequestDTO request) {
        Restaurant restaurant = restaurantRepository.findById(request.restaurantId()).orElseThrow(()->
                new RuntimeException("Restaurant not found"));
        Food food = foodRepository.findById(request.foodId()).orElseThrow(()->
                new RuntimeException("Food not found"));
        if(Objects.equals(restaurant.getOwner().getId(), user.getId())){
            food.setPrice(request.price());
            foodRepository.save(food);
            return mapStructFood.FOOD_DTO(food);
        }
        else throw new RuntimeException("You do not have access to this section");
    }

    public FoodDTO deleteFood(User user, long id) {
        Food food = foodRepository.findById(id).orElseThrow(()->
                new RuntimeException("Food not found"));
        if (Objects.equals(food.getRestaurant().getOwner().getId(), user.getId())){
            foodRepository.delete(food);
            return mapStructFood.FOOD_DTO(food);
        }
        else throw new RuntimeException("You do not have access to this section");
    }
}
