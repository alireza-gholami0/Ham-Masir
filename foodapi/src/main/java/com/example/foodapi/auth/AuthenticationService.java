package com.example.foodapi.auth;

import com.example.foodapi.config.JwtService;
import com.example.foodapi.domain.User;
import com.example.foodapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthResponse signIn(SignupRequest request) {

    if(repository.findByEmail(request.email()).isPresent()){
      throw new RuntimeException("The email entered has already been registered");
    }
    else {
      var user = User.builder()
              .firstName(request.firstname())
              .lastName(request.lastname())
              .address(request.address())
              .email(request.email())
              .password(passwordEncoder.encode(request.password()))
              .role(request.role())
              .build();
      var savedUser = repository.save(user);
      var jwtToken = jwtService.generateToken(user);
      return AuthResponse.builder()
              .token(jwtToken)
              .build();
    }

  }

  public AuthResponse logIn(LoginRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.email(),
            request.password()
        )
    );
    var user = repository.findByEmail(request.email())
            .orElseThrow(() -> new RuntimeException("Email or password is incorrect"));
    var jwtToken = jwtService.generateToken(user);
    return AuthResponse.builder()
        .token(jwtToken)
        .build();
  }
}
