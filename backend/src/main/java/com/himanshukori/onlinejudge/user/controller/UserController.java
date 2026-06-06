package com.himanshukori.onlinejudge.user.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/me")
    public String currentUser(
            Authentication authentication
    ) {
        return authentication.getName();
    }
}