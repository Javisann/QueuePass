package com.queuepass.apirest.controller.DTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@JsonPropertyOrder({"username", "message", "status", "jwt"})
public record AuthResponse(
        String username,
        String name,
        String message,
        List<GrantedAuthority> authorities,
        String token,
        Boolean status) {
}
