package com.example.foodapi.service;

import com.example.foodapi.domain.*;
import com.example.foodapi.payload.OrderRequest;
import com.example.foodapi.repository.*;
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

    public void saveOrder(String email, String restaurantName, List<OrderRequest> requests) {
        User user = userRepository.getUserByEmail(email);
        if (user != null){
            Restaurant restaurant = restaurantRepository.findByName(restaurantName);
            if (restaurant != null){
                Order order = new Order();
                order.setClient(user);
                order.setRestaurant(restaurant);
                order.setDateTime(LocalDateTime.now());
                orderRepository.save(order);
                requests.forEach(x->{
                    OrderFood orderFood = new OrderFood();
                    orderFood.setOrder(order);
                    orderFood.setFood(foodRepository.getFoodByNameAndRestaurant(x.foodName(),restaurant));
                    if (orderFood.getFood() == null){
                        throw new RuntimeException(x.foodName() + " Not found");
                    }
                    else {
                        orderFood.setQuantity(x.quantity());
                        orderFoodRepository.save(orderFood);
                    }
                });
            }
            else throw new RuntimeException(restaurantName + " Not found");
        }
        else throw new RuntimeException(email + " Not found");

    }
}
