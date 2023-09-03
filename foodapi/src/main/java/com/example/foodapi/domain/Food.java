package com.example.foodapi.domain;

import com.example.foodapi.view.FoodView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor
public class Food implements FoodView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "foodId")
    private Long id;
    @Column(name = "foodName")
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "food")
    private List<OrderFood> orderFoods = new ArrayList<>();
}

