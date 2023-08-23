package com.example.foodapi.service;

import com.example.foodapi.domain.Food;
import com.example.foodapi.domain.Restaurant;
import com.example.foodapi.payload.FoodResponse;
import com.example.foodapi.payload.RestaurantRequest;
import com.example.foodapi.payload.RestaurantResponse;
import com.example.foodapi.repository.FoodRepository;
import com.example.foodapi.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private FoodRepository foodRepository;
    public List<RestaurantResponse> getAllRestaurant(){
        List<RestaurantResponse> restaurantResponses = new ArrayList<RestaurantResponse>();
        for(Restaurant restaurant:restaurantRepository.findAll().stream().toList()){
            restaurantResponses.add(new RestaurantResponse(restaurant.getName(),restaurant.getAddress(),restaurant.getPhoneNumber()));
        }
        return restaurantResponses;
    }
    public List<FoodResponse>getRestaurantMenu(RestaurantRequest request){
        Restaurant restaurant = restaurantRepository.findByName(request.name());
        if (restaurant != null){
            List<FoodResponse> menu = new ArrayList<>();
            for (Food food:restaurant.getFoods()) {
                menu.add(new FoodResponse(food.getName(),food.getPrice(),food.getDescription()));
            }
            return menu;
        }
        throw new RuntimeException("Restaurant not found");
    }
}
