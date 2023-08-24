package com.example.foodapi.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "order_food")
public class OrderFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Food food;

    private int quantity;
}
