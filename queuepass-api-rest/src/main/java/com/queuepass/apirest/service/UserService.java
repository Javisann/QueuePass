package com.queuepass.apirest.service;

import com.queuepass.apirest.DTO.UserDTO;
import com.queuepass.apirest.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<UserDTO> findAll();
    public Optional<UserDTO> findById(Long id);
    public Optional<UserDTO> findByUsername(String username);
    public User save(User user);
    public void deleteById(Long id);
    public boolean existsByUsername(String username);
}
