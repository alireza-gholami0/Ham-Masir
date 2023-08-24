package com.example.foodapi.controller;


import com.example.foodapi.payload.OrderRequest;
import com.example.foodapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/{email}/{restaurantName}")
    public void saveOrder(@PathVariable String email, @PathVariable String restaurantName, @RequestBody List<OrderRequest> requests){
        orderService.saveOrder(email, restaurantName, requests);
    }
}
