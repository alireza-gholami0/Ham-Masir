package com.example.foodapi.service;

import com.example.foodapi.domain.Restaurant;
import com.example.foodapi.domain.User;
import com.example.foodapi.dto.entity.FoodDTO;
import com.example.foodapi.dto.entity.RestaurantDTO;
import com.example.foodapi.mapper.MapStructRestaurant;
import com.example.foodapi.dto.RestaurantRequestDTO;
import com.example.foodapi.repository.FoodRepository;
import com.example.foodapi.repository.RestaurantRepository;
import com.example.foodapi.repository.specification.RestaurantSpecifications;
import com.example.foodapi.service.cache.RestaurantCacheInitializer;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final MapStructRestaurant mapStructRestaurant;
    private final FoodRepository foodRepository;
    private final RestaurantCacheInitializer restaurantCache;
    public Restaurant restaurantValidation(long id){
        return restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }
    public List<RestaurantDTO> getRestaurants(String name, String city, String address){
        Specification<Restaurant> spec = RestaurantSpecifications.searchByFilters(name, city, address);
        return mapStructRestaurant.RESTAURANT_DTOS(restaurantRepository.findAll(spec));
    }
    public List<FoodDTO> getRestaurantMenu(long id){
        return foodRepository.findByRestaurant(id, FoodDTO.class);
    }

    public RestaurantDTO addRestaurant(RestaurantRequestDTO request, User user){
        Restaurant restaurant = Restaurant.builder()
                .name(request.name())
                .address(request.address())
                .phoneNumber(request.phone_number())
                .city(request.city())
                .owner(user)
                .build();
        restaurant.setOwner(user);
        restaurantRepository.save(restaurant);
        return mapStructRestaurant.RESTAURANT_DTO(restaurant);
    }

    public RestaurantDTO deleteRestaurant(long id, User user) {
        Restaurant restaurant = restaurantValidation(id);
        if (Objects.equals(restaurant.getOwner().getId(), user.getId()) || user.getRole().name().equals("ROLE_ADMIN")){
            restaurantRepository.delete(restaurant);
            RestaurantDTO dto = mapStructRestaurant.RESTAURANT_DTO(restaurant);
            restaurantCache.getRestaurantSet().remove(dto);
            return dto;
        }
        else throw new RuntimeException("You do not have access to this section");
    }
    public List<RestaurantDTO> getCache(){
        return restaurantCache.getRestaurantSet().stream().toList();
    }

    public RestaurantDTO editRestaurant(long id, User currentUser, RestaurantRequestDTO request) {
        Restaurant restaurant = restaurantValidation(id);
        restaurantCache.getRestaurantSet().remove(mapStructRestaurant.RESTAURANT_DTO(restaurant));
        if (Objects.equals(restaurant.getOwner().getId(), currentUser.getId()) || currentUser.getRole().name().equals("ROLE_ADMIN")){
            if (request.name() != null){
                restaurant.setName(request.name());
            }
            if (request.address() != null){
                restaurant.setAddress(request.address());
            }
            if (request.city() != null){
                restaurant.setCity(request.city());
            }
            if (request.phone_number() != null){
                restaurant.setPhoneNumber(request.phone_number());
            }
            restaurantRepository.save(restaurant);
            return mapStructRestaurant.RESTAURANT_DTO(restaurant);
        }
        else throw new RuntimeException("You do not have access to this section");
    }
}
