package com.example.foodapi.controller;


import com.example.foodapi.domain.User;
import com.example.foodapi.dto.entity.OrderDTO;
import com.example.foodapi.dto.entity.OrderFoodDTO;
import com.example.foodapi.service.OrderService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;
    @PostMapping("/add/{restaurantId}")
    public ResponseEntity<OrderDTO> saveOrder(@AuthenticationPrincipal User user, @PathVariable long restaurantId, @Valid @RequestBody List<OrderFoodDTO> requests){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.saveOrder(user, restaurantId, requests));
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<OrderDTO> delete(@PathVariable long id, @AuthenticationPrincipal User user){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.delete(id, user));
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<OrderDTO> getByOrderId(@PathVariable long id, @AuthenticationPrincipal User user){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getByOrderId(id, user));
    }
    @GetMapping("/get-by-restaurant-id/{id}")
    @RolesAllowed({"ROLE_ADMIN","ROLE_OWNER"})
    public ResponseEntity<List<OrderDTO>> getByRestaurantId(@PathVariable long id, @AuthenticationPrincipal User user){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getByRestaurantId(id, user));
    }
}
