package com.queuepass.apirest.controller.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AuthCreateUserRequest(@NotBlank String username,
                                    @NotBlank String name,
                                    @NotBlank String password
                                    ) {
}
