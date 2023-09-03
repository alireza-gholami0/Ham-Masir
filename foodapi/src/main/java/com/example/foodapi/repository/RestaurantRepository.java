package com.example.foodapi.repository;

import com.example.foodapi.domain.Restaurant;
import com.example.foodapi.dto.RestaurantDTO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, JpaSpecificationExecutor<Restaurant> {
    List<RestaurantDTO> findAll(Specification<RestaurantDTO> spec, Class<RestaurantDTO> restaurantDTOClass);
}
