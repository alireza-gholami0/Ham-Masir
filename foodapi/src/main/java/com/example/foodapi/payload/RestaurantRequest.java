package com.example.foodapi.payload;

public record RestaurantRequest(String email, String name, String address, String phone_number) {
}
