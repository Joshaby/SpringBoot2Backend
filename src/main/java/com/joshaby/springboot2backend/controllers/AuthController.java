package com.joshaby.springboot2backend.controllers;

import com.joshaby.springboot2backend.dto.EmailDTO;
import com.joshaby.springboot2backend.security.User;
import com.joshaby.springboot2backend.security.util.JWTUtil;
import com.joshaby.springboot2backend.services.AuthService;
import com.joshaby.springboot2backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/refresh_token")
    public void refreshToken(HttpServletResponse response) {
        User user = userService.getUserAuthenticated();
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
    }

    @PostMapping(value = "/forgot")
    public void forgot(@Valid @RequestBody EmailDTO dto) {
        authService.sendNewPassword(dto.getEmail());
    }
}
