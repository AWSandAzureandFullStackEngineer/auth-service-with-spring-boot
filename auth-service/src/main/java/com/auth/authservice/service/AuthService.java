package com.auth.authservice.service;

import com.auth.authservice.domain.User;

public interface AuthService {
    User registerUser(String username, String password);

    User loadUserByUsername(String username);
}
