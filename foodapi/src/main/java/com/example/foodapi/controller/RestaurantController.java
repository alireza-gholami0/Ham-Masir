package com.example.foodapi.controller;

import com.example.foodapi.config.CurrentUser;
import com.example.foodapi.domain.User;
import com.example.foodapi.payload.FoodResponse;
import com.example.foodapi.payload.RestaurantRequest;
import com.example.foodapi.payload.RestaurantResponse;
import com.example.foodapi.service.RestaurantService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;
    @GetMapping("/get-all")
    public ResponseEntity<List<RestaurantResponse>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getAllRestaurant());
    }
    @GetMapping("/menu/{id}")
    public ResponseEntity<List<FoodResponse>> getMenu(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getRestaurantMenu(id));
    }
    @PostMapping("/add")
    @RolesAllowed("ROLE_OWNER")
    public ResponseEntity<RestaurantResponse> addOwner(@RequestBody RestaurantRequest request, @CurrentUser User currentUser ){
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.addRestaurant(request, currentUser));
    }
    @DeleteMapping("/delete/{id}")
    @RolesAllowed("ROLE_OWNER")
    public ResponseEntity<RestaurantResponse> delete(@PathVariable long id, @CurrentUser User currentUser){
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.deleteRestaurant(id, currentUser));
    }

}
