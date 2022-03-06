package com.joshaby.springboot2backend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joshaby.springboot2backend.dto.CredenciasDTO;
import com.joshaby.springboot2backend.security.util.JWTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager manager;

    private JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager manager, JWTUtil jwtUtil) {
        this.manager = manager;
        this.jwtUtil = jwtUtil;
        setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            CredenciasDTO dto = new ObjectMapper().readValue(request.getInputStream(), CredenciasDTO.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    dto.getEmail(), dto.getSenha(), new ArrayList<>());
            return manager.authenticate(token);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication)
            throws IOException, ServletException {

        String username = ((UserDetailsImpl) authentication.getPrincipal()).getUsername();
        String token = jwtUtil.generateToken(username);
        response.addHeader("Authorization", "Bearer " + token);
    }

    private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

        @Override
        public void onAuthenticationFailure(
                HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
                throws IOException, ServletException {

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.getWriter().append(json());
        }

        private String json() {
            long date = new Date().getTime();

            return "{\"timestamp\": " + date + ", " +
                    "\"status\": 401" + ", " +
                    "\"error\": \"Não autorizado\"" + ", " +
                    "\"message\": \"Email ou senha inválidos\"" + ", " +
                    "\"path\": \"/login\"" +
                    "}";
        }
    }
}
