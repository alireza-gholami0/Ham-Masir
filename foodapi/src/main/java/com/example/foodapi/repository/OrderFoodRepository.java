package com.example.foodapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFoodRepository extends JpaRepository<OrderRepository, Long> {
}
