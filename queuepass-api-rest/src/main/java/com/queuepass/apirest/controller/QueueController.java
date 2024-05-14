package com.queuepass.apirest.controller;

import com.queuepass.apirest.model.QueueModel;
import com.queuepass.apirest.service.queue.QueueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/queue")
@CrossOrigin
public class QueueController {

    @Autowired
    private QueueServiceImpl queueService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<QueueModel>> findAll(){
        List<QueueModel> usersInQueue = this.queueService.findAll();
        if(!usersInQueue.isEmpty()){
            return ResponseEntity.ok(usersInQueue);
        }
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        this.queueService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User deleted");
    }

}
