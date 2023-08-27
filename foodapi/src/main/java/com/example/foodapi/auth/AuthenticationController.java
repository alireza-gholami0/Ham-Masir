package com.example.foodapi.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/sign-in")
  public ResponseEntity<AuthResponse> signIn(
      @RequestBody SignupRequest request
  ) {
    return ResponseEntity.ok(service.signIn(request));
  }
  @PostMapping("/log-in")
  public ResponseEntity<AuthResponse> logIn(
      @RequestBody LoginRequest request
  ) {
    return ResponseEntity.ok(service.logIn(request));
  }
}
