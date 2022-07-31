package com.joshaby.springboot2backend.services;

import com.joshaby.springboot2backend.security.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserDetailsImpl getUserAuthenticated() {
        return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
