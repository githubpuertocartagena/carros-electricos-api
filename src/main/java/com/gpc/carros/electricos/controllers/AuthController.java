package com.gpc.carros.electricos.controllers;

import com.gpc.carros.electricos.model.response.AuthResponse;
import com.gpc.carros.electricos.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody String encryptedData) {
       return ResponseEntity.ok(authService.login(encryptedData));
    }


    @PostMapping("/token")
    public ResponseEntity<AuthResponse> validate(@RequestBody String token) {
        try {
            return ResponseEntity.ok(authService.verifyToken(token));
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            return ResponseEntity.status(401).body(null);
        }
    }

    @GetMapping("/public-key")
    public String getPublicKey() {
        return authService.publicKey();
    }


}
