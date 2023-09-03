package com.example.foodapi.repository.specification;

import com.example.foodapi.domain.Restaurant;
import com.example.foodapi.dto.RestaurantDTO;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;


public class RestaurantSpecifications {
    public static Specification<Restaurant> searchByFilters(String name, String city, String address) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (name != null && !name.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }

            if (city != null && !city.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("city"), city));
            }

            if (address != null && !address.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("address"), "%" + address + "%"));
            }

            return predicate;
        };
    }
}