package com.gpc.carros.electricos.controllers;

import com.gpc.carros.electricos.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class UserController {

    @Autowired
    private JwtService jwtService;

    @GetMapping("/user/email")
    public ResponseEntity<String> getEmail(@AuthenticationPrincipal final Jwt jwt) {
        return jwtService.getEmail(jwt);
    }


}
