package com.himanshukori.onlinejudge.auth.service;

import com.himanshukori.onlinejudge.auth.dto.AuthResponse;
import com.himanshukori.onlinejudge.auth.dto.LoginRequest;
import com.himanshukori.onlinejudge.auth.dto.RegisterRequest;
import com.himanshukori.onlinejudge.security.JwtService;
import com.himanshukori.onlinejudge.user.entity.Role;
import com.himanshukori.onlinejudge.user.entity.User;
import com.himanshukori.onlinejudge.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse login(LoginRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(
                        () -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException(
                    "Invalid credentials");
        }

        String token = jwtService.generateToken(
                user.getEmail());

        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(
                user.getEmail());

        return AuthResponse.builder()
                .token(token)
                .build();
    }
}