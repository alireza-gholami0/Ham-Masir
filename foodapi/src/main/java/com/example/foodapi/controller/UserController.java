package com.example.foodapi.controller;

import com.example.foodapi.domain.User;
import com.example.foodapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService service;
    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }
    @PostMapping("/adduser")
    public void addUser(@RequestBody User newUser){
        service.saveUser(newUser);
    }
    @GetMapping("/getusers")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }
    @GetMapping("/getuser/{id}")
    public ResponseEntity<User> getUsers(@PathVariable long id) {
        return ResponseEntity.ok(service.getUserById(id));
    }
}
