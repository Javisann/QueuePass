package com.queuepass.apirest.DTO;

public record UserDTO(
        Long id,
        String username,
        String email
) { }