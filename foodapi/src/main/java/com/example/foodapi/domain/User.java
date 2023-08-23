package com.example.foodapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Role role;
    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private List<Restaurant> ownedRestaurant;
    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Order> userOrders;
}
