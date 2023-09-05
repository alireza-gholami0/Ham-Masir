package com.example.foodapi.controller;


import com.example.foodapi.domain.User;
import com.example.foodapi.dto.entity.OrderDTO;
import com.example.foodapi.dto.entity.OrderFoodDTO;
import com.example.foodapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/add/{restaurantId}")
    public ResponseEntity<List<OrderFoodDTO>> saveOrder(@AuthenticationPrincipal User user, @PathVariable long restaurantId, @RequestBody List<OrderFoodDTO> requests){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.saveOrder(user, restaurantId, requests));
    }
    @GetMapping("total-price/{id}")
    public ResponseEntity<OrderDTO> getTotal(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getTotalPrice(id));
    }
}
