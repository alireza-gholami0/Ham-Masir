package com.example.foodapi.repository;

import com.example.foodapi.domain.Food;
import com.example.foodapi.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food,Long> {
    Food getFoodByNameAndRestaurant(String name, Restaurant restaurant);
}
