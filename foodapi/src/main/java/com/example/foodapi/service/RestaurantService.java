package com.example.foodapi.service;

import com.example.foodapi.domain.Restaurant;
import com.example.foodapi.domain.User;
import com.example.foodapi.dto.FoodDTO;
import com.example.foodapi.dto.RestaurantDTO;
import com.example.foodapi.mapper.MapStructFood;
import com.example.foodapi.mapper.MapStructRestaurant;
import com.example.foodapi.dto.AddRestaurantRequestDTO;
import com.example.foodapi.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class RestaurantService {
    private RestaurantRepository restaurantRepository;
    private MapStructRestaurant mapStructRestaurant;
    private MapStructFood mapStructFood;
    public List<RestaurantDTO> getAllRestaurant(){
        return mapStructRestaurant.RESTAURANT_DTOS(restaurantRepository.findAll());
    }
    public List<FoodDTO>getRestaurantMenu(long id){
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(()->new RuntimeException("Restaurant not found"));
        return mapStructFood.FOOD_DTO_LIST(restaurant.getFoods());
    }

    public RestaurantDTO addRestaurant(AddRestaurantRequestDTO request, User user){
        Restaurant restaurant = Restaurant.builder()
                .name(request.name())
                .address(request.address())
                .phoneNumber(request.phone_number())
                .owner(user)
                .build();
        restaurant.setOwner(user);
        restaurantRepository.save(restaurant);
        return mapStructRestaurant.RESTAURANT_DTO(restaurant);
    }

    public RestaurantDTO deleteRestaurant(long id, User user) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException("Restaurant not found"));
        if (Objects.equals(restaurant.getOwner().getId(), user.getId())){
            restaurantRepository.delete(restaurant);
            return mapStructRestaurant.RESTAURANT_DTO(restaurant);
        }
        else throw new RuntimeException("You do not have access to this section");
    }
}
