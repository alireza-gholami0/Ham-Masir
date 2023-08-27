package com.example.foodapi.auth;

import com.example.foodapi.domain.RoleType;
import lombok.Builder;

@Builder
public record SignupRequest(String firstname, String lastname, String address,
                            String email, String password, RoleType role) { }
