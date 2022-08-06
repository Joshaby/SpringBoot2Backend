package com.joshaby.springboot2backend.controllers;

import com.joshaby.springboot2backend.dto.EmailDTO;
import com.joshaby.springboot2backend.security.UserDetailsImpl;
import com.joshaby.springboot2backend.security.util.JWTUtil;
import com.joshaby.springboot2backend.services.AuthService;
import com.joshaby.springboot2backend.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth Controller", description = "providencia novo token JWT, e envio de emails com novas senhas")
public class AuthController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/refresh_token")
    @Operation(
            summary = "Post token JWT",
            description = "Gera um novo token JWT a partir um outro token JWT perto de expirar",
            security = @SecurityRequirement(name = "bearer-key"))
    public void refreshToken(HttpServletResponse response) {
        UserDetailsImpl userDetailsImpl = userService.getUserAuthenticated();
        String token = jwtUtil.generateToken(userDetailsImpl.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
    }

    @PostMapping("/forgot")
    @Operation(summary = "Post Email", description = "Gera uma nova senha e envia ao email informado")
    public void forgot(@Valid @RequestBody EmailDTO dto) {
        authService.sendNewPassword(dto.getEmail());
    }
}
