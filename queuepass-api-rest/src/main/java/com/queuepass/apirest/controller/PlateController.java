package com.queuepass.apirest.controller;

import com.queuepass.apirest.model.PlateModel;
import com.queuepass.apirest.service.plate.PlateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/plate")
@PreAuthorize("denyAll()")
public class PlateController {

    @Autowired
    PlateServiceImpl plateService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/type/{type}")
    public ResponseEntity<ArrayList<PlateModel>> findByType(@PathVariable String type) {
        ArrayList<PlateModel> plates = this.plateService.findByType(type);
        if (plates.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(plates);
        }
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/name/{name}")
    public ResponseEntity<PlateModel> findByName(@PathVariable String name) {
        Optional<PlateModel> plate = this.plateService.findByName(name);
        if (plate.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(plate.get());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PlateModel> create(@RequestBody PlateModel plate) {
        //comprobaciones
        if (plate.getId() != null) {
            return ResponseEntity.badRequest().build();
        } else {
            this.plateService.save(plate);
            return ResponseEntity.status(HttpStatus.CREATED).body(plate);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<PlateModel> update(@RequestBody PlateModel plate) {
        //comprobaciones
        this.plateService.save(plate);
        return ResponseEntity.status(HttpStatus.CREATED).body(plate);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        this.plateService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
