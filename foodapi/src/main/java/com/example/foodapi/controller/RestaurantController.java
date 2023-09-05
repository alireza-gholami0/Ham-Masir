package com.example.foodapi.controller;

import com.example.foodapi.domain.User;
import com.example.foodapi.dto.entity.FoodDTO;
import com.example.foodapi.dto.entity.RestaurantDTO;
import com.example.foodapi.dto.RestaurantRequestDTO;
import com.example.foodapi.service.RestaurantService;
import com.example.foodapi.service.cache.RestaurantCacheInitializer;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@RolesAllowed({"ROLE_OWNER", "ROLE_ADMIN"})
@AllArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
    @GetMapping("/get-all")
    @RolesAllowed({"ROLE_OWNER", "ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<List<RestaurantDTO>> getRestaurants(
            @RequestParam(name = "name", required = false) String name, @RequestParam(name = "city", required = false) String city, @RequestParam(name = "address", required = false) String address
    ){
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getRestaurants(name,city,address));
    }
    @GetMapping("/get-all/cache")
    @RolesAllowed({"ROLE_OWNER", "ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<List<RestaurantDTO>> getCache(){
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getCache());
    }
    @GetMapping("/menu/{id}")
    @RolesAllowed({"ROLE_OWNER", "ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<List<FoodDTO>> getMenu(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getRestaurantMenu(id));
    }
    @PostMapping("/add")
    public ResponseEntity<RestaurantDTO> add(@Valid @RequestBody RestaurantRequestDTO request, @AuthenticationPrincipal User currentUser ){
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.addRestaurant(request, currentUser));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RestaurantDTO> delete(@PathVariable long id, @AuthenticationPrincipal User currentUser){
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.deleteRestaurant(id, currentUser));
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<RestaurantDTO> edit(@PathVariable long id, @AuthenticationPrincipal User currentUser, @RequestBody RestaurantRequestDTO request){
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.editRestaurant(id, currentUser, request));
    }
}
