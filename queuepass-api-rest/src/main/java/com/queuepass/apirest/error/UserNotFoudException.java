package com.queuepass.apirest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoudException extends RuntimeException {

    public UserNotFoudException(Long id) {
        super("No se ha encontrado ningun usuario con la id: " + id);
    }

    public UserNotFoudException(String username) {
        super("No se ha encontrado ningun usuario con el username: " + username);
    }
}