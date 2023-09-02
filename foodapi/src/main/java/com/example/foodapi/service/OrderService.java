package com.example.foodapi.service;

import com.example.foodapi.domain.*;
import com.example.foodapi.dto.OrderFoodDTO;
import com.example.foodapi.mapper.MapStructOrderFood;
import com.example.foodapi.repository.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private RestaurantRepository restaurantRepository;
    private FoodRepository foodRepository;
    private OrderFoodRepository orderFoodRepository;
    private MapStructOrderFood mapStructOrderFood;
    @Transactional
    public List<OrderFoodDTO> saveOrder(User user, long restaurantId, List<OrderFoodDTO> requests) {
        List<OrderFoodDTO> responses = new ArrayList<>();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()->
                new RuntimeException("Restaurant not found"));
        Order order = Order.builder()
                .client(user)
                .dateTime(LocalDateTime.now())
                .restaurant(restaurant)
                .build();
        orderRepository.save(order);
        requests.forEach(x->{
            Food food = foodRepository.findById(x.foodId()).orElseThrow(()->
                    new RuntimeException("Food not found"));
            OrderFood orderFood = mapStructOrderFood.ORDER_FOOD(x);
            orderFood.setOrder(order);
            orderFoodRepository.save(orderFood);
            responses.add(new OrderFoodDTO(x.foodId(),x.quantity()));
        });
        return responses;
    }
}
