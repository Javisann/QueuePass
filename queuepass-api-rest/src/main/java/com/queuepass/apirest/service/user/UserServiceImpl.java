package com.queuepass.apirest.service.user;

import com.queuepass.apirest.controller.DTO.UserDTO;
import com.queuepass.apirest.model.UserModel;
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
                .map(user -> new UserDTO(user.getId(), user.getUsername()))
                .toList();
    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        return this.repository.findById(id).map(user -> new UserDTO(user.getId(), user.getUsername()));
    }

    @Override
    public Optional<UserDTO> findByUsername(String email) {
        return this.repository.findByUsername(email).map(user -> new UserDTO(user.getId(), user.getUsername()));
    }

    @Override
    public UserModel save(UserModel user) {
        return this.repository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public boolean existsByUsername(String email) {
        return this.repository.existsByUsername(email);
    }
}
