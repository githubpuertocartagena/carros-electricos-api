package com.gpc.carros.electricos.services.impl;

import com.gpc.carros.electricos.model.TokenResponse;
import com.gpc.carros.electricos.model.response.AuthResponse;
import com.gpc.carros.electricos.services.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class AuthServiceImpl implements AuthService {

    @Value("${gpc.external.authentication.url}")
    private String serverUrl;

    @Override
    public TokenResponse login(String information) {
        String introspectionEndpoint = serverUrl + "/auth/login";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> request = new HttpEntity<>(information, headers);

        ResponseEntity<TokenResponse> response = restTemplate.exchange(introspectionEndpoint, HttpMethod.POST, request, TokenResponse.class);

        return response.getBody();
    }

    @Override
    public AuthResponse verifyToken(String token) {

        String introspectionEndpoint = serverUrl + "/auth/token";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> request = new HttpEntity<>(token, headers);

        ResponseEntity<AuthResponse> response = restTemplate.exchange(introspectionEndpoint, HttpMethod.POST, request, AuthResponse.class);

        return response.getBody();
    }

    @Override
    public String publicKey() {
        String introspectionEndpoint = serverUrl + "/security/public-key";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(introspectionEndpoint,String.class);

        return response.getBody();
    }


}
