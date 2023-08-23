package com.example.foodapi.service;

import com.example.foodapi.domain.Restaurant;
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
            restaurantResponses.add(new RestaurantResponse(restaurant.getName(),restaurant.getAddress(),restaurant.getPhoneNumber()));
        }
        return restaurantResponses;
    }
}
