package com.example.foodapi.service;

import com.example.foodapi.domain.*;
import com.example.foodapi.dto.entity.OrderDTO;
import com.example.foodapi.dto.entity.OrderFoodDTO;
import com.example.foodapi.repository.*;
import com.example.foodapi.repository.specification.OrderDao;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private RestaurantRepository restaurantRepository;
    private FoodRepository foodRepository;
    private OrderFoodRepository orderFoodRepository;

    public Order orderValidation(long id){
        return orderRepository.findById(Long.valueOf(id))
                .orElseThrow(()->new RuntimeException("Order Not found"));
    }
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
            OrderFood orderFood = OrderFood.builder()
                    .food(food)
                    .order(order)
                    .quantity(x.quantity())
                    .build();
            orderFoodRepository.save(orderFood);
            responses.add(new OrderFoodDTO(x.foodId(),x.quantity()));
        });
        return responses;
    }
    public OrderDTO getTotalPrice(long id){
        orderValidation(id);
        return new OrderDTO(id,OrderDao.totalPrice(id));
    }
}
