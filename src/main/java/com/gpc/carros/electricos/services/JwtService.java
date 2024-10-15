package com.gpc.carros.electricos.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;

public interface JwtService {

    ResponseEntity<String>getEmail(Jwt jwt);
}
