package com.queuepass.apirest.repository;

import com.queuepass.apirest.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    public Optional<UserModel> findByEmail(String email);
    public boolean existsByEmail(String email);
}