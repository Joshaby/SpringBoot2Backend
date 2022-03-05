package com.joshaby.springboot2backend.config;

import com.joshaby.springboot2backend.security.JWTAuthenticationFilter;
import com.joshaby.springboot2backend.security.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] PUBLIC_MATCHES = {
            "/h2-console/**"
    };

    private static final String[] PUBLIC_MATCHES_GET = {
            "/produtos/**",
            "/categorias/**",
            "/clientes/**"
    };

    private static final String[] PUBLIC_MATCHES_POST = {
            "/clientes/**",
            "/auth/forgot/**"
    };

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private Environment environment;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {
            security.headers().frameOptions().disable();
        }
        security.cors().and().csrf().disable();
        security.authorizeRequests()
                .antMatchers(HttpMethod.POST, PUBLIC_MATCHES_POST).permitAll()
                .antMatchers(HttpMethod.GET, PUBLIC_MATCHES_GET).permitAll()
                .antMatchers(PUBLIC_MATCHES).permitAll()
                .anyRequest().authenticated();
        security.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
        security.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder managerBuilder) throws Exception {
        managerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "OPTIONS"));
        final UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", configuration);
        return configurationSource;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
