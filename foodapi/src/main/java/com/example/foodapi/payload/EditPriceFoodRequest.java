package com.example.foodapi.payload;

public record EditPriceFoodRequest(String restaurantName, String foodName, double price) {
}
