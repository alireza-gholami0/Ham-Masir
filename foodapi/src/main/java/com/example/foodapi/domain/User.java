package com.example.foodapi.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    @ManyToOne
    private Role role;
    @OneToMany(mappedBy = "owner")
    private List<Restaurant> ownedRestaurant;
    @OneToMany(mappedBy = "client")
    private List<Order> userOrders;
}
