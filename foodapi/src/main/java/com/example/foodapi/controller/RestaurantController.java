package com.example.foodapi.controller;

import com.example.foodapi.payload.RestaurantResponse;
import com.example.foodapi.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;
    @GetMapping("/getall")
    public List<RestaurantResponse> getAll(){
        return restaurantService.getAllRestaurant();
    }
}
