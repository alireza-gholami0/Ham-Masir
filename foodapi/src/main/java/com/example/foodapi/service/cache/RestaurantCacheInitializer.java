package com.example.foodapi.service.cache;


import com.example.foodapi.domain.Restaurant;
import com.example.foodapi.dto.entity.RestaurantDTO;
import com.example.foodapi.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import java.util.List;


@Component
@AllArgsConstructor
@Getter
@ShellComponent
public class RestaurantCacheInitializer {
    private final RedissonClient redissonClient;
    private final RestaurantRepository restaurantRepository;
    public static RSet<RestaurantDTO> restaurantSet;
    private RestaurantDTO convertRestaurantToCacheData(Restaurant restaurant) {
        return new RestaurantDTO(restaurant.getId(), restaurant.getName(), restaurant.getCity(),
                restaurant.getAddress(), restaurant.getPhoneNumber());
    }
    @Scheduled(fixedRate = 60000)
    @ShellMethod(value = "updateRestaurantCache",key = "rc")
    public void updateRestaurantCache(){
        List<Restaurant> restaurants = restaurantRepository.findAll();
        List<RestaurantDTO> cacheDataList = restaurants.stream()
                .map(this::convertRestaurantToCacheData)
                .toList();
        restaurantSet = redissonClient.getSet("restaurantsCache", new TypedJsonJacksonCodec(RestaurantDTO.class));
        restaurantSet.clear();
        restaurantSet.addAll(cacheDataList);
//        System.out.println(restaurantSet.stream().toList().get(0).name());
    }
    @PostConstruct
    public void init(){
        restaurantSet = redissonClient.getSet("restaurantsCache", new TypedJsonJacksonCodec(RestaurantDTO.class));
    }
}
