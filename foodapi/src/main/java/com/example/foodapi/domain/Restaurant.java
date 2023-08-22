package com.example.foodapi.domain;

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
    private List<Food> foods;
    @ManyToOne
    private User owner;
    @OneToMany(mappedBy = "restaurant")
    private List<Order> restaurantOrders;
}
