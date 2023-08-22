package com.example.foodapi.repositories;

import com.example.foodapi.domain.OrderFoodId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFoodRepository extends JpaRepository<OrderRepository, OrderFoodId> {
}
