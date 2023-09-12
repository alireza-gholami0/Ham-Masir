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
import java.util.Objects;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final OrderFoodRepository orderFoodRepository;

    public Order orderValidation(long id){
        return orderRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Order Not found"));
    }
    @Transactional
    public OrderDTO saveOrder(User user, long restaurantId, List<OrderFoodDTO> requests) {
        List<OrderFoodDTO> orderFoodDTOs = new ArrayList<>();
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
            orderFoodDTOs.add(new OrderFoodDTO(x.foodId(),x.quantity()));
        });

        return new OrderDTO(order.getId(), orderFoodDTOs, orderFoodRepository.totalPrice(order.getId()));
    }

    public OrderDTO delete(long id, User user) {
        Order order = orderValidation(id);
        if (Objects.equals(order.getClient().getId(), user.getId()) ||
                Objects.equals(order.getRestaurant().getOwner().getId(), user.getId()) ||
                user.getRole().name().equals("ROLE_ADMIN")){
            OrderDTO response = new OrderDTO(order.getId(), orderFoodRepository.findByOrder_Id(order.getId()), OrderDao.totalPrice(id));
            orderRepository.delete(order);
            return response;
        }
        else throw new RuntimeException("You do not have access to this section");

    }

    public OrderDTO getByOrderId(long id, User user){
        Order order = orderValidation(id);
        if (Objects.equals(order.getClient().getId(), user.getId()) ||
                Objects.equals(order.getRestaurant().getOwner().getId(), user.getId()) ||
                user.getRole().name().equals("ROLE_ADMIN")){
            return new OrderDTO(order.getId(), orderFoodRepository.findByOrder_Id(order.getId()), OrderDao.totalPrice(id));
        }
        else throw new RuntimeException("You do not have access to this section");
    }
    public List<OrderDTO> getByRestaurantId(long id, User user){
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException("Restaurant not found"));
        List<OrderDTO> response = new ArrayList<>();
        if (Objects.equals(restaurant.getOwner().getId(), user.getId()) || user.getRole().name().equals("ROLE_ADMIN")){
            List<Order> orders = orderRepository.findByRestaurant(restaurant);
            orders.forEach(x->
                    response.add(new OrderDTO(x.getId(), orderFoodRepository.findByOrder_Id(x.getId()), OrderDao.totalPrice(x.getId()))));
            return response;
        }
        else throw new RuntimeException("You do not have access to this section");
    }
}
