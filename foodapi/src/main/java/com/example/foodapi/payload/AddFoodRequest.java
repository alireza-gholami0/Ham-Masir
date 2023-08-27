package com.example.foodapi.payload;

public record AddFoodRequest(long restaurantId, String foodName, String description, double price) {
}
