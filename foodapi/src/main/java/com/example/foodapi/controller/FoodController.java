package com.example.foodapi.controller;

import com.example.foodapi.domain.User;
import com.example.foodapi.dto.EditFoodDTO;
import com.example.foodapi.dto.AddFoodDTO;
import com.example.foodapi.dto.entity.FoodDTO;
import com.example.foodapi.service.FoodService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/food")
@RolesAllowed({"ROLE_OWNER","ROLE_ADMIN"})
public class FoodController {
    private final FoodService foodService;
    @PostMapping("/add")
    public ResponseEntity<FoodDTO> add(@AuthenticationPrincipal User user, @Valid @RequestBody AddFoodDTO request){
        return ResponseEntity.status(HttpStatus.OK).body(foodService.addFood(user,request));
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<FoodDTO> editPrice(@AuthenticationPrincipal User user, @PathVariable long id, @RequestBody EditFoodDTO request){
        return ResponseEntity.status(HttpStatus.OK).body(foodService.editFood(user,id,request));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<FoodDTO> delete(@AuthenticationPrincipal User user, @PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(foodService.deleteFood(user,id));
    }
    @GetMapping("/get/{id}")
    @RolesAllowed({"ROLE_OWNER","ROLE_ADMIN","ROLE_USER"})
    public ResponseEntity<FoodDTO> get(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(foodService.getFood(id));
    }
}
