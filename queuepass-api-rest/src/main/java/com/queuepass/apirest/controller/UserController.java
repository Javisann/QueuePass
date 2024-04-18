package com.queuepass.apirest.controller;

import com.queuepass.apirest.DTO.UserDTO;
import com.queuepass.apirest.error.UserNotFoudException;
import com.queuepass.apirest.model.User;
import com.queuepass.apirest.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<UserDTO> users = this.userService.findAll();
        if(!users.isEmpty()){
            return ResponseEntity.ok(users);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/id/{id}")
    public UserDTO findById(@PathVariable Long id){
        return this.userService.findById(id)
                .orElseThrow(() -> new UserNotFoudException(id));
    }


    @GetMapping("/username/{username}")
    public UserDTO findByUsername(@PathVariable String username){
        return this.userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFoudException(username));
    }


    @PostMapping
    public ResponseEntity<String> create(@RequestBody User user){
        if(user.getId() == null){
            if(this.userService.existsByUsername(user.getUsername())) {
                return ResponseEntity.badRequest().body("Username not available");
            }
            this.userService.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created");
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody User user){
        UserDTO userComparator = this.userService.findById(user.getId()).orElseGet(null);
        if(userComparator.id() != null){
            if(!user.getUsername().equals(userComparator.username()) && this.userService.existsByUsername(user.getUsername())){
                return ResponseEntity.badRequest().body("Username not available");
            }
            this.userService.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User updated");
        }
        return ResponseEntity.badRequest().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        this.userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
