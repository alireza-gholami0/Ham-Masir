package com.example.foodapi.repository;

import com.example.foodapi.domain.OrderFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFoodRepository extends JpaRepository<OrderFood, Long> {
}
