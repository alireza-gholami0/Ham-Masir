package com.example.foodapi.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;

    private String description;
    @ManyToOne
    private Restaurant restaurant;
}
