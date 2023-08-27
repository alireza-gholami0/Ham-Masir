package com.example.foodapi.payload;

import com.example.foodapi.domain.RoleType;

public record UserResponse(long id, String first_name, String last_name, String address, String email, RoleType role) {
}
