package com.example.foodapi.controller;

import com.example.foodapi.payload.FoodResponse;
import com.example.foodapi.payload.RestaurantRequest;
import com.example.foodapi.payload.RestaurantResponse;
import com.example.foodapi.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/menu/{name}")
    public List<FoodResponse> getMenu(@PathVariable String name){
        return restaurantService.getRestaurantMenu(name);
    }
    @PostMapping("/add")
    public void add(@RequestBody RestaurantRequest request){
        restaurantService.addRestaurant(request);
    }
}
