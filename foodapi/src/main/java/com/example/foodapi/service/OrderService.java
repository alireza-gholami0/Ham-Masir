package com.example.foodapi.service;

import com.example.foodapi.domain.*;
import com.example.foodapi.payload.OrderRequest;
import com.example.foodapi.payload.OrderResponse;
import com.example.foodapi.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private OrderFoodRepository orderFoodRepository;
    @Transactional
    public List<OrderResponse> saveOrder(User user, long restaurantId, List<OrderRequest> requests) {
        List<OrderResponse> responses = new ArrayList<>();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()->
                new RuntimeException("Restaurant not found"));
        Order order = Order.builder()
                .client(user)
                .dateTime(LocalDateTime.now())
                .restaurant(restaurant)
                .build();
        orderRepository.save(order);
        requests.forEach(x->{
            Food food = foodRepository.findById(x.id()).orElseThrow(()->
                    new RuntimeException("Food not found"));
            OrderFood orderFood = OrderFood.builder()
                    .food(food)
                    .order(order)
                    .quantity(x.quantity())
                    .build();
            orderFoodRepository.save(orderFood);
            responses.add(new OrderResponse(x.id(),x.quantity()));
        });
        return responses;
    }
}
