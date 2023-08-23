package com.example.foodapi.repository;

import com.example.foodapi.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
    Restaurant findByName(String name);
    boolean existsByName(String name);

//    boolean existsRestaurantByName(String name);
}
