package com.example.foodapi.service;

import com.example.foodapi.domain.Food;
import com.example.foodapi.domain.Restaurant;
import com.example.foodapi.domain.RoleType;
import com.example.foodapi.domain.User;
import com.example.foodapi.payload.FoodResponse;
import com.example.foodapi.payload.RestaurantRequest;
import com.example.foodapi.payload.RestaurantResponse;
import com.example.foodapi.repository.FoodRepository;
import com.example.foodapi.repository.RestaurantRepository;
import com.example.foodapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private UserRepository userRepository;
    public List<RestaurantResponse> getAllRestaurant(){
        List<RestaurantResponse> restaurantResponses = new ArrayList<RestaurantResponse>();
        for(Restaurant restaurant:restaurantRepository.findAll().stream().toList()){
            restaurantResponses.add(new RestaurantResponse(restaurant.getName(),restaurant.getAddress(),restaurant.getPhoneNumber()));
        }
        return restaurantResponses;
    }
    public List<FoodResponse>getRestaurantMenu(String name){
        Restaurant restaurant = restaurantRepository.findByName(name);
        if (restaurant != null){
            List<FoodResponse> menu = new ArrayList<>();
            for (Food food:restaurant.getFoods()) {
                menu.add(new FoodResponse(food.getName(),food.getPrice(),food.getDescription()));
            }
            return menu;
        }
        throw new RuntimeException("Restaurant not found");
    }

    public void addRestaurant(RestaurantRequest request){
        User user = userRepository.getUserByEmail(request.email());
        if (user != null){
            if (user.getRole().getName().equals("OWNER")){
                if (!restaurantRepository.existsByName(request.name())){
                    Restaurant restaurant = new Restaurant();
                    restaurant.setName(request.name());
                    restaurant.setAddress(request.address());
                    restaurant.setPhoneNumber(request.phone_number());
                    restaurant.setOwner(user);
                    restaurantRepository.save(restaurant);
                }
                else throw new RuntimeException("This restaurant has already been registered");
            }
            else throw new RuntimeException("You do not have access to this section");
        }
        else throw new RuntimeException("User not found");
    }

    public void deleteRestaurant(String email, String name) {
        User user = userRepository.getUserByEmail(email);
        Restaurant restaurant = restaurantRepository.findByName(name);
        if (user != null && restaurant != null){
            if (user.getRole().getName().equals("OWNER") && restaurant.getOwner().equals(user)){
                restaurantRepository.delete(restaurant);
            }
            else throw new RuntimeException("You do not have access to this section");
        }
        else throw new RuntimeException("User or Restaurant not found");
    }
}
