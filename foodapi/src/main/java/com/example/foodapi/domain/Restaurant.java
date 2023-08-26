package com.example.foodapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NaturalId
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
