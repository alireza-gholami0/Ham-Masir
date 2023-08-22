package com.example.foodapi.domain;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class OrderFoodId implements Serializable {
    private Long orderId;
    private Long foodId;

}