package com.example.foodapi.controller;

import com.example.foodapi.domain.User;
import com.example.foodapi.dto.FoodDTO;
import com.example.foodapi.dto.RestaurantDTO;
import com.example.foodapi.dto.AddRestaurantRequestDTO;
import com.example.foodapi.service.RestaurantService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;
    @GetMapping("/get-all")
    public ResponseEntity<List<RestaurantDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getAllRestaurant());
    }
    @GetMapping("/menu/{id}")
    public ResponseEntity<List<FoodDTO>> getMenu(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getRestaurantMenu(id));
    }
    @PostMapping("/add")
    @RolesAllowed("ROLE_OWNER")
    public ResponseEntity<RestaurantDTO> addOwner(@RequestBody AddRestaurantRequestDTO request, @AuthenticationPrincipal User currentUser ){
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.addRestaurant(request, currentUser));
    }
    @DeleteMapping("/delete/{id}")
    @RolesAllowed("ROLE_OWNER")
    public ResponseEntity<RestaurantDTO> delete(@PathVariable long id, @AuthenticationPrincipal User currentUser){
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.deleteRestaurant(id, currentUser));
    }

}
