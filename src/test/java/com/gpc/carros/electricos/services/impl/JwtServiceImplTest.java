package com.gpc.carros.electricos.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.ResponseEntity.ok;

class JwtServiceImplTest {

    private JwtServiceImpl jwtService;

    @Mock
    private Jwt jwt;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtService = new JwtServiceImpl();
    }

    @Test
    public void testGetEmail_WhenUniqueNameIsPresent() {
        final String expectedEmail = "test@example.com";
        when(jwt.getClaimAsString("unique_name")).thenReturn(expectedEmail);

        final ResponseEntity<String> response = jwtService.getEmail(jwt);

        assertEquals(ok(expectedEmail), response);
    }

    @Test
    public void testGetEmail_WhenUniqueNameIsNotPresent() {
        when(jwt.getClaimAsString("unique_name")).thenReturn(null);

        final ResponseEntity<String> response = jwtService.getEmail(jwt);

        assertEquals(ResponseEntity.status(UNAUTHORIZED).body("Token inválido: 'unique_name' no presente"), response);
    }

}