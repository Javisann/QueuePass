package com.queuepass.apirest.repository;

import com.queuepass.apirest.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    public Optional<UserModel> findByUsername(String email);
    public boolean existsByUsername(String email);

    @Query(value = """
            SELECT u.name
            FROM UserModel u
            where u.username = :username""")
    public String findNameByUsername(@Param("username") String username);
}