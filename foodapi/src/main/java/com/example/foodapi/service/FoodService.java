package com.example.foodapi.service;

import com.example.foodapi.domain.Food;
import com.example.foodapi.domain.Restaurant;
import com.example.foodapi.domain.User;
import com.example.foodapi.payload.AddFoodRequest;
import com.example.foodapi.payload.EditPriceFoodRequest;
import com.example.foodapi.payload.FoodResponse;
import com.example.foodapi.repository.FoodRepository;
import com.example.foodapi.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    public FoodResponse addFood(User user, AddFoodRequest request) {
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
            return new FoodResponse(food.getId(), food.getName(), food.getPrice(), food.getDescription());
        }
        else throw new RuntimeException("You do not have access to this section");

    }

    public FoodResponse editFoodPrice(User user, EditPriceFoodRequest request) {
        Restaurant restaurant = restaurantRepository.findById(request.restaurantId()).orElseThrow(()->
                new RuntimeException("Restaurant not found"));
        Food food = foodRepository.findById(request.foodId()).orElseThrow(()->
                new RuntimeException("Food not found"));
        if(Objects.equals(restaurant.getOwner().getId(), user.getId())){
            food.setPrice(request.price());
            foodRepository.save(food);
            return new FoodResponse(food.getId(), food.getName(), food.getPrice(), food.getDescription());
        }
        else throw new RuntimeException("You do not have access to this section");
    }

    public FoodResponse deleteFood(User user, long id) {
        Food food = foodRepository.findById(id).orElseThrow(()->
                new RuntimeException("Food not found"));
        if (Objects.equals(food.getRestaurant().getOwner().getId(), user.getId())){
            foodRepository.delete(food);
            return new FoodResponse(food.getId(), food.getName(), food.getPrice(), food.getDescription());
        }
        else throw new RuntimeException("You do not have access to this section");
    }
}
