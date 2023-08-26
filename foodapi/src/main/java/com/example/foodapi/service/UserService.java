package com.example.foodapi.service;

import com.example.foodapi.domain.User;
import com.example.foodapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{
    private UserRepository repository;
    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    public User getUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public void saveUser(User user) {
        repository.save(user);
    }

    public void deleteUser(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new RuntimeException("User not found with id: " + id);
        }
    }
}
