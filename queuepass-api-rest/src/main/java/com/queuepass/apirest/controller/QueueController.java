package com.queuepass.apirest.controller;

import com.queuepass.apirest.error.ApiError;
import com.queuepass.apirest.error.UserNotFoudException;
import com.queuepass.apirest.model.QueueModel;
import com.queuepass.apirest.service.queue.QueueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/id/{id}")
    public ResponseEntity<Integer> positionCount(@PathVariable Long id){
        return ResponseEntity.ok(this.queueService.positionCount(id));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/people")
    public ResponseEntity<Integer> peopleInQueue(){
        return ResponseEntity.ok(this.queueService.peopleInQueue());
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<QueueModel> create(@RequestBody QueueModel model){
        if(model.getId() == null){
            return ResponseEntity.status(HttpStatus.CREATED).body( this.queueService.save(model));
        }
        return ResponseEntity.badRequest().build();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        this.queueService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User deleted");
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/name/{name}")
    public QueueModel findByName(@PathVariable String name){
        return this.queueService.findQueueModelByName(name)
                .orElseThrow(() -> new UserNotFoudException(name));
    }
}
