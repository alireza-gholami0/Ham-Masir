package com.example.foodapi.service.cache;


import com.example.foodapi.domain.Restaurant;
import com.example.foodapi.repository.RestaurantRepository;
import com.example.foodapi.service.RestaurantService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.stereotype.Component;


import jakarta.annotation.PostConstruct;


import java.util.List;


@Component
@AllArgsConstructor
@Getter
public class RestaurantCacheInitializer {
    private final RedissonClient redissonClient;
    private final RestaurantService restaurantService;
    private final RestaurantRepository restaurantRepository;
    public static RSet<CacheData> restaurantSet;


    @Getter
    @Setter
    public static class CacheData {
        private long id;
        private String name;
    }

    private CacheData convertRestaurantToCacheData(Restaurant restaurant) {
        CacheData cacheData = new CacheData();
        cacheData.setId(restaurant.getId());
        cacheData.setName(restaurant.getName());
        return cacheData;
    }

    @PostConstruct
    public void cacheAllRestaurants() {

        List<Restaurant> restaurants = restaurantRepository.findAll();
        List<CacheData> cacheDataList = restaurants.stream()
                .map(this::convertRestaurantToCacheData)
                .toList();


        restaurantSet = redissonClient.getSet("restaurantsCache", new TypedJsonJacksonCodec(CacheData.class));
        restaurantSet.clear();
        restaurantSet.addAll(cacheDataList);
        System.out.println(restaurantSet.stream().toList().get(0).name);
    }
}
