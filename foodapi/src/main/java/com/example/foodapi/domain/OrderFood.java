package com.example.foodapi.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "order_food")
public class OrderFood {

    @EmbeddedId
    private OrderFoodId id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("foodId")
    @JoinColumn(name = "food_id")
    private Food food;

    @Column(name = "quantity")
    private int quantity;

}

