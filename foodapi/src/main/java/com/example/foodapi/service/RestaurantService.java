package com.example.foodapi.service;

import com.example.foodapi.domain.Restaurant;
import com.example.foodapi.domain.User;
import com.example.foodapi.dto.FoodDTO;
import com.example.foodapi.dto.RestaurantDTO;
import com.example.foodapi.mapper.MapStructFood;
import com.example.foodapi.mapper.MapStructRestaurant;
import com.example.foodapi.dto.AddRestaurantRequestDTO;
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
    private RestaurantRepository restaurantRepository;
    private MapStructRestaurant mapStructRestaurant;
    private FoodRepository foodRepository;
    public List<RestaurantDTO> getRestaurants(String name, String city, String address){
        Specification<Restaurant> spec = RestaurantSpecifications.searchByFilters(name, city, address);
        return mapStructRestaurant.RESTAURANT_DTOS(restaurantRepository.findAll(spec));
    }
    public List<FoodDTO> getRestaurantMenu(long id){
        return foodRepository.findByRestaurant(id, FoodDTO.class);
    }

    public RestaurantDTO addRestaurant(AddRestaurantRequestDTO request, User user){
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
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException("Restaurant not found"));
        if (Objects.equals(restaurant.getOwner().getId(), user.getId())){
            restaurantRepository.delete(restaurant);
            return mapStructRestaurant.RESTAURANT_DTO(restaurant);
        }
        else throw new RuntimeException("You do not have access to this section");
    }
    public List<RestaurantCacheInitializer.CacheData> getCache(){
        return RestaurantCacheInitializer.restaurantSet.stream().toList();
    }
}
