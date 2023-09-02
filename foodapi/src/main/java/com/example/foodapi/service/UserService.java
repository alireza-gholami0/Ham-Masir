package com.example.foodapi.service;

import com.example.foodapi.domain.User;
import com.example.foodapi.dto.UserDTO;
import com.example.foodapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService{
    private UserRepository repository;
    public UserDTO getUserById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getAddress(), user.getEmail(), user.getRole());
    }

    public List<UserDTO> getAllUsers() {
        List<UserDTO> responses = new ArrayList<>();
        for (User user:repository.findAll()
             ) {
            responses.add(new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getAddress(),
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

    public UserDTO deleteUser(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        repository.deleteById(id);
        return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getAddress(),
                user.getEmail(), user.getRole());
    }
}
