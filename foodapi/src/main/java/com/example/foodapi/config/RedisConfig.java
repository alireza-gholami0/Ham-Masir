package com.example.foodapi.config;

import com.example.foodapi.dto.entity.RestaurantDTO;
import org.redisson.Redisson;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

//    @Bean(destroyMethod = "shutdown")
    @Bean
    public RedissonClient redissonClient() {
        return Redisson.create();
    }
    @Bean
    public RSet<RestaurantDTO> restaurantSet(RedissonClient redissonClient) {
        return redissonClient.getSet("restaurantsCache", new TypedJsonJacksonCodec(RestaurantDTO.class));
    }
}