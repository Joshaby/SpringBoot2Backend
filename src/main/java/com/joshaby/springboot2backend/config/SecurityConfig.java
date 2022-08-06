package com.joshaby.springboot2backend.config;

import com.joshaby.springboot2backend.security.JWTAuthenticationFilter;
import com.joshaby.springboot2backend.security.JWTAuthorizationFilter;
import com.joshaby.springboot2backend.security.util.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private static final String[] PUBLIC_MATCHES = {
            "/h2-console/**",
            "/openapi/**"
    };

    private static final String[] PUBLIC_MATCHES_GET = {
            "/produtos/**",
            "/categorias/**"
    };

    private static final String[] PUBLIC_MATCHES_POST = {
            "/clientes/**",
            "/auth/forgot/**"
    };

    private UserDetailsService userDetailsService;

    private Environment environment;

    private JWTUtil jwtUtil;

    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {
            security.headers().frameOptions().disable();
        }
        security.cors().and().csrf().disable();
        security.authorizeRequests()
                .antMatchers(HttpMethod.POST, PUBLIC_MATCHES_POST).permitAll()
                .antMatchers(HttpMethod.GET, PUBLIC_MATCHES_GET).permitAll()
                .antMatchers(PUBLIC_MATCHES).permitAll()
                .anyRequest().authenticated();
        AuthenticationManager manager = authenticationManager(authenticationConfiguration);
        security.addFilter(new JWTAuthenticationFilter(manager, jwtUtil));
        security.addFilter(new JWTAuthorizationFilter(manager, jwtUtil, userDetailsService));
        security.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return security.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS", "PATCH"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
