package com.gpc.carros.electricos.services;

import com.gpc.carros.electricos.model.TokenResponse;
import com.gpc.carros.electricos.model.response.AuthResponse;

public interface AuthService {
    TokenResponse login(String information);
    AuthResponse verifyToken(String token);
    String publicKey();
}
