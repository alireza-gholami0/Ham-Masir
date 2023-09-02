package com.example.foodapi.controller;

import com.example.foodapi.domain.User;
import com.example.foodapi.dto.*;
import com.example.foodapi.dto.AddFoodRequestDTO;
import com.example.foodapi.dto.EditPriceFoodRequestDTO;
import com.example.foodapi.service.FoodService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    private FoodService foodService;
    @PostMapping("/add")
    @RolesAllowed("ROLE_OWNER")
    public ResponseEntity<FoodDTO> add(@AuthenticationPrincipal User user, @RequestBody AddFoodRequestDTO request){
        return ResponseEntity.status(HttpStatus.OK).body(foodService.addFood(user,request));
    }
    @PutMapping("/edit-price")
    @RolesAllowed("ROLE_OWNER")
    public ResponseEntity<FoodDTO> editPrice(@AuthenticationPrincipal User user, @RequestBody EditPriceFoodRequestDTO request){
        return ResponseEntity.status(HttpStatus.OK).body(foodService.editFoodPrice(user,request));
    }
    @DeleteMapping("/delete/{id}")
    @RolesAllowed("ROLE_OWNER")
    public ResponseEntity<FoodDTO> delete(@AuthenticationPrincipal User user, @PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(foodService.deleteFood(user,id));
    }
}
