package com.example.foodapi.services;

import com.example.foodapi.domain.User;
import com.example.foodapi.exceptions.UserException;
import com.example.foodapi.repositories.UserRepository;
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
                .orElseThrow(() -> new UserException("User not found with id: " + id));
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
            throw new UserException("User not found with id: " + id);
        }
    }
}
