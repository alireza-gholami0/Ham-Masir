package com.example.foodapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Enumerated(EnumType.STRING)
    private String name;
    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private List<User> users;
}
