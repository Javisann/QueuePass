package com.queuepass.apirest.service.user;

import com.queuepass.apirest.controller.DTO.UserDTO;
import com.queuepass.apirest.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<UserDTO> findAll();

    public Optional<UserDTO> findById(Long id);

    public Optional<UserDTO> findByUsername(String email);

    public UserModel save(UserModel user);

    public void deleteById(Long id);

    public boolean existsByUsername(String email);
}
