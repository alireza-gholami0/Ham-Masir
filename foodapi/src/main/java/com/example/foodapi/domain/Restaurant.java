package com.example.foodapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    @OneToMany(mappedBy = "restaurant")
    @JsonIgnore
    private List<Food> foods;
    @ManyToOne
    @JsonIgnore
    private User owner;
    @OneToMany(mappedBy = "restaurant")
    @JsonIgnore
    private List<Order> restaurantOrders;
}
