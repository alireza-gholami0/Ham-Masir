package com.example.foodapi.domain;

import com.example.foodapi.domain.log.DatabaseLogger;
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
@EntityListeners(DatabaseLogger.class)
public class Food{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private List<OrderFood> orderFoods = new ArrayList<>();

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", restaurant_id='" + restaurant.getId() + '\'' +
                '}';
    }
}

