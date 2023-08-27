package com.example.foodapi.service;

import com.example.foodapi.auth.SignupRequest;
import com.example.foodapi.domain.User;
import com.example.foodapi.payload.UserResponse;
import com.example.foodapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService{
    private UserRepository repository;
    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    public UserResponse getUserById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getAddress(), user.getEmail(), user.getRole());
    }

    public List<UserResponse> getAllUsers() {
        List<UserResponse> responses = new ArrayList<>();
        for (User user:repository.findAll()
             ) {
            responses.add(new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getAddress(),
                    user.getEmail(), user.getRole()));
        }
        return responses;
    }

//    public void saveUser(SignupRequest user) {
//        repository.save(User.builder()
//                .firstName(user.firstname())
//                .lastName(user.lastname())
//                .address(user.address())
//                .role(user.role())
//                .password(user.password())
//                .email(user.email())
//                .build());
//    }

    public UserResponse deleteUser(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        repository.deleteById(id);
        return new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getAddress(),
                user.getEmail(), user.getRole());
    }
}
