package com.example.foodapi.controller;


import com.example.foodapi.domain.User;
import com.example.foodapi.payload.UserResponse;
import com.example.foodapi.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
//    @PostMapping("/add")
//    @RolesAllowed("ROLE_ADMIN")
//    public ResponseEntity<UserResponse> addUser(@RequestBody SignupRequest newUser){
//        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.signIn(newUser));
//    }
    @GetMapping("/get-all")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<List<UserResponse>> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }
    @GetMapping("/get/{id}")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<UserResponse> getUsers(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }
    @DeleteMapping("/delete/{id}")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(id));    }
}
