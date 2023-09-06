package com.example.foodapi.repository;

import com.example.foodapi.domain.Order;
import com.example.foodapi.domain.OrderFood;
import com.example.foodapi.dto.entity.OrderFoodDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderFoodRepository extends JpaRepository<OrderFood, Long> {
    @Query("SELECT new com.example.foodapi.dto.entity.OrderFoodDTO(of.food.id, of.quantity) FROM OrderFood of WHERE of.order.id = :orderId")
    List<OrderFoodDTO> findByOrder_Id(@Param("orderId") long order);

    @Query("select sum (of.food.price * of.quantity) from OrderFood of where of.order.id = :id")
    double totalPrice(@Param("id") long id);
}
