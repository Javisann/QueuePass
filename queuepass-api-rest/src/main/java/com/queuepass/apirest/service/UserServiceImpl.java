package com.queuepass.apirest.service;

import com.queuepass.apirest.DTO.UserDTO;
import com.queuepass.apirest.model.User;
import com.queuepass.apirest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository repository;

    @Override
    public List<UserDTO> findAll() {
        return this.repository.findAll().stream()
                .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getEmail()))
                .toList();
    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        return this.repository.findById(id).map(user -> new UserDTO(user.getId(), user.getUsername(), user.getEmail()));
    }

    @Override
    public Optional<UserDTO> findByUsername(String username) {
        return this.repository.findByUsername(username).map(user -> new UserDTO(user.getId(), user.getUsername(), user.getEmail()));
    }

    @Override
    public User save(User user) {
        return this.repository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return this.repository.existsByUsername(username);
    }
}
