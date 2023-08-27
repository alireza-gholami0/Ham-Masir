package com.example.foodapi.payload;

public record EditPriceFoodRequest(long restaurantId, long foodId, double price) {
}
