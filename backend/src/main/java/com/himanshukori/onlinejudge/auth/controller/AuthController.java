package com.himanshukori.onlinejudge.auth.controller;

import com.himanshukori.onlinejudge.auth.dto.AuthResponse;
import com.himanshukori.onlinejudge.auth.dto.LoginRequest;
import com.himanshukori.onlinejudge.auth.dto.RegisterRequest;
import com.himanshukori.onlinejudge.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(
            @Valid @RequestBody RegisterRequest request) {

        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequest request) {

        return authService.login(request);
    }
}