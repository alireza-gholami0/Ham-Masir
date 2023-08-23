package com.example.foodapi.payload;

public record AddFoodRequest(String restaurantName,String foodName, String description, double price) {
}
