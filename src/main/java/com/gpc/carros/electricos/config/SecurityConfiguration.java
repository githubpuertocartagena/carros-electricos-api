package com.gpc.carros.electricos.config;

import com.gpc.carros.electricos.exceptions.CustomAccessDeniedHandler;
import com.gpc.carros.electricos.exceptions.CustomAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@SuppressWarnings({"PMD.AvoidCatchingGenericException", "PMD.AvoidThrowingRawExceptionTypes"})
public class SecurityConfiguration {

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String jwkSetUri;

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) {
        try {
            http
                    .authorizeRequests()
                    .anyRequest().authenticated() // cualquier otra ruta necesita autenticación
                    .and()
                    .oauth2ResourceServer()
                    .jwt()
                    .decoder(jwtDecoder())
                    .and()
                    .authenticationEntryPoint(new CustomAuthenticationEntryPoint()) // Manejo de errores en autenticación
                    .accessDeniedHandler(new CustomAccessDeniedHandler()); // Manejo de errores de autorización
            return http.build();
        } catch (Exception e) {
            throw new RuntimeException("Error configuring SecurityFilterChain", e);
        }
    }
    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder
                .withJwkSetUri(jwkSetUri)
                .jwsAlgorithm(SignatureAlgorithm.RS256)
                .build();
    }
}