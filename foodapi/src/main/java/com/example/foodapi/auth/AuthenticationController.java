package com.example.foodapi.auth;


import com.example.foodapi.service.rabbitmq.RabbitMQProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;
  private final RabbitMQProducer producer;

  @PostMapping("/sign-in")
  public ResponseEntity<AuthResponse> signIn(
      @RequestBody SignupRequest request
  ) {
    return ResponseEntity.ok(service.signIn(request));
  }
  @PostMapping("/log-in")
  public ResponseEntity<AuthResponse> logIn(@RequestBody LoginRequest request) {
    AuthResponse response = service.logIn(request);
    producer.loginLog(request.email(), LocalDateTime.now());
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
