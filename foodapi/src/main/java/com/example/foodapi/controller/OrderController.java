package com.example.foodapi.controller;


import com.example.foodapi.config.CurrentUser;
import com.example.foodapi.domain.User;
import com.example.foodapi.payload.OrderRequest;
import com.example.foodapi.payload.OrderResponse;
import com.example.foodapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/add/{restaurantId}")
    public ResponseEntity<List<OrderResponse>> saveOrder(@CurrentUser User user, @PathVariable long restaurantId, @RequestBody List<OrderRequest> requests){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.saveOrder(user, restaurantId, requests));
    }
}
