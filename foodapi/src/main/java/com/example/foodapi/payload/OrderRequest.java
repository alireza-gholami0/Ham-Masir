package com.example.foodapi.payload;

import com.example.foodapi.domain.Food;

import java.util.List;

public record OrderRequest(long id, int quantity) {
}
