package com.gpc.carros.electricos.model.response;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    String username;
    String name;
    boolean active;
}
