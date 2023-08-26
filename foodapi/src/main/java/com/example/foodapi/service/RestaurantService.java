package com.example.foodapi.service;

import com.example.foodapi.domain.Food;
import com.example.foodapi.domain.Restaurant;
import com.example.foodapi.domain.User;
import com.example.foodapi.payload.FoodResponse;
import com.example.foodapi.payload.RestaurantRequest;
import com.example.foodapi.payload.RestaurantResponse;
import com.example.foodapi.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    public List<RestaurantResponse> getAllRestaurant(){
        List<RestaurantResponse> restaurantResponses = new ArrayList<RestaurantResponse>();
        for(Restaurant restaurant:restaurantRepository.findAll().stream().toList()){
            restaurantResponses.add(new RestaurantResponse(restaurant.getId(), restaurant.getName(),restaurant.getAddress(),restaurant.getPhoneNumber()));
        }
        return restaurantResponses;
    }
    public List<FoodResponse>getRestaurantMenu(long id){
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(()->new RuntimeException("Restaurant not found"));
        List<FoodResponse> menu = new ArrayList<>();
        for (Food food:restaurant.getFoods()) {
            menu.add(new FoodResponse(food.getName(),food.getPrice(),food.getDescription()));
        }
        return menu;
    }

    public RestaurantResponse addRestaurant(RestaurantRequest request, User user){
        Restaurant restaurant = Restaurant.builder()
                .name(request.name())
                .address(request.address())
                .phoneNumber(request.phone_number())
                .owner(user).build();
        restaurantRepository.save(restaurant);
        return new RestaurantResponse(restaurant.getId(),restaurant.getName(),restaurant.getAddress(),restaurant.getPhoneNumber());
    }

    public RestaurantResponse deleteRestaurant(long id, User user) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException("Restaurant not found"));
        if (restaurant != null){
            if (restaurant.getOwner().equals(user)){
                restaurantRepository.delete(restaurant);
                return new RestaurantResponse(restaurant.getId(),restaurant.getName(),restaurant.getAddress(),restaurant.getPhoneNumber());
            }
            else throw new RuntimeException("You do not have access to this section");
        }
        else throw new RuntimeException("Restaurant not found");
    }
}
