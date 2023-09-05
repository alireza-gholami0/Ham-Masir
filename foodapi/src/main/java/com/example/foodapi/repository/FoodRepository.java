package com.example.foodapi.repository;

import com.example.foodapi.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food,Long> {
    @Query("select new com.example.foodapi.dto.entity.FoodDTO(f.id,f.name,f.description,f.price) from Food f join f.restaurant r where r.id = :id")
    <T> List<T> findByRestaurant(@Param("id") Long id, Class<T> type);
}
