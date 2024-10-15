package com.gpc.carros.electricos.services.impl;

import com.gpc.carros.electricos.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Service
public class JwtServiceImpl implements JwtService {

    @Override
    public ResponseEntity<String> getEmail(final Jwt jwt) {
        final String email = jwt.getClaimAsString("unique_name");
        if (email == null){
            return ResponseEntity.status(UNAUTHORIZED).body("Token inválido: 'unique_name' no presente");
        }
        return ResponseEntity.ok(email);
    }
}
