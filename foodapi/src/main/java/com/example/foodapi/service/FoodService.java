package com.example.foodapi.service;

import com.example.foodapi.domain.Food;
import com.example.foodapi.domain.Restaurant;
import com.example.foodapi.domain.User;
import com.example.foodapi.payload.AddFoodRequest;
import com.example.foodapi.payload.EditPriceFoodRequest;
import com.example.foodapi.repository.FoodRepository;
import com.example.foodapi.repository.RestaurantRepository;
import com.example.foodapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private UserRepository userRepository;

    public void addFood(String email, AddFoodRequest request) {
        User user = userRepository.getUserByEmail(email);
        Restaurant restaurant = restaurantRepository.findByName(request.restaurantName());
        if (user != null && restaurant != null){
            if (user.getRole().getName().equals("OWNER") && restaurant.getOwner().equals(user)){
                Food food = new Food();
                food.setName(request.foodName());
                food.setDescription(request.description());
                food.setPrice(request.price());
                food.setRestaurant(restaurant);
                foodRepository.save(food);
            }
            else throw new RuntimeException("You do not have access to this section");
        }
        else throw new RuntimeException("User or Restaurant not found");
    }

    public void editFoodPrice(String email, EditPriceFoodRequest request) {
        User user = userRepository.getUserByEmail(email);
        Restaurant restaurant = restaurantRepository.findByName(request.restaurantName());
//        Food food = (Food) restaurant.getFoods().stream().filter(x->x.getName().equals(request.foodName())).toArray()[0];
        Food food = foodRepository.getFoodByNameAndRestaurant(request.foodName(),restaurant);
        if (user != null && restaurant != null){
            if (user.getRole().getName().equals("OWNER") && restaurant.getOwner().equals(user)){
                if(food != null){
                    food.setPrice(request.price());
                    foodRepository.save(food);
                }
                else throw new RuntimeException("Food not found");
            }
            else throw new RuntimeException("You do not have access to this section");
        }
        else throw new RuntimeException("User or Restaurant not found");
    }
}
