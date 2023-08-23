package com.example.foodapi.controller;

import com.example.foodapi.payload.AddFoodRequest;
import com.example.foodapi.payload.DeleteFoodRequest;
import com.example.foodapi.payload.EditPriceFoodRequest;
import com.example.foodapi.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    private FoodService foodService;
    @PostMapping("/add/{email}")
    public void add(@PathVariable String email, @RequestBody AddFoodRequest request){
        foodService.addFood(email,request);
    }
    @PutMapping("editprice/{email}")
    public void editPrice(@PathVariable String email, @RequestBody EditPriceFoodRequest request){
        foodService.editFoodPrice(email,request);
    }
    @DeleteMapping("delete/{email}")
    public void delete(@PathVariable String email, @RequestBody DeleteFoodRequest request){
        foodService.deleteFood(email,request);
    }
}
