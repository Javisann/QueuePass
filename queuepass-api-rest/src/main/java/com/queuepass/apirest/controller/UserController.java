package com.queuepass.apirest.controller;

import com.queuepass.apirest.controller.DTO.UserDTO;
import com.queuepass.apirest.error.UserNotFoudException;
import com.queuepass.apirest.model.UserModel;
import com.queuepass.apirest.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> users = this.userService.findAll();
        if (!users.isEmpty()) {
            return ResponseEntity.ok(users);
        }
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/id/{id}")
    public UserDTO findById(@PathVariable Long id) {
        return this.userService.findById(id)
                .orElseThrow(() -> new UserNotFoudException(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/username/{username}")
    public UserDTO findByUsername(@PathVariable String username) {
        return this.userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFoudException(username));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PutMapping
    public ResponseEntity<String> update(@RequestBody UserModel user) {
        UserDTO userComparator = this.userService.findById(user.getId()).orElseGet(null);
        if (userComparator.id() != null) {
            if (!user.getUsername().equals(userComparator.username()) && this.userService.existsByUsername(user.getUsername())) {
                return ResponseEntity.badRequest().body("Username not available");
            }
            this.userService.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User updated");
        }
        return ResponseEntity.badRequest().build();

    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        this.userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User deleted");
    }
}
