package com.example.foodapi.service;

import com.example.foodapi.domain.Food;
import com.example.foodapi.domain.Restaurant;
import com.example.foodapi.domain.User;
import com.example.foodapi.dto.EditFoodDTO;
import com.example.foodapi.dto.entity.FoodDTO;
import com.example.foodapi.mapper.MapStructFood;
import com.example.foodapi.dto.AddFoodDTO;
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
    public Food foodValidation(long id){
        return foodRepository.findById(id).orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }
    public FoodDTO addFood(User user, AddFoodDTO request) {
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

    public FoodDTO editFood(User user, long id, EditFoodDTO request) {
        Food food = foodValidation(id);
        if(Objects.equals(food.getRestaurant().getOwner().getId(), user.getId())){
            if (request.foodName() != null){
                food.setName(request.foodName());
            }
            if (request.price() != 0){
                food.setPrice(request.price());
            }
            if (request.description() != null){
                food.setDescription(request.description());
            }
            foodRepository.save(food);
            return mapStructFood.FOOD_DTO(food);
        }
        else throw new RuntimeException("You do not have access to this section");
    }

    public FoodDTO deleteFood(User user, long id) {
        Food food = foodValidation(id);
        if (Objects.equals(food.getRestaurant().getOwner().getId(), user.getId())){
            foodRepository.delete(food);
            return mapStructFood.FOOD_DTO(food);
        }
        else throw new RuntimeException("You do not have access to this section");
    }
}
