package com.queuepass.apirest.controller;

import com.queuepass.apirest.model.Plate;
import com.queuepass.apirest.service.PlateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/plate")
public class PlateController {

    @Autowired
    PlateServiceImpl plateService;

    @GetMapping("/type/{type}")
    public ResponseEntity<ArrayList<Plate>> findByType(@PathVariable String type) {
        ArrayList<Plate> plates = this.plateService.findByType(type);
        if (plates.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(plates);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Plate> findByName(@PathVariable String name) {
        Optional<Plate> plate = this.plateService.findByName(name);
        if (plate.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(plate.get());
        }
    }

    @PostMapping
    public ResponseEntity<Plate> create(@RequestBody Plate plate) {
        //comprobaciones
        if (plate.getId() != null) {
            return ResponseEntity.badRequest().build();
        } else {
            this.plateService.save(plate);
            return ResponseEntity.status(HttpStatus.CREATED).body(plate);
        }
    }

    @PutMapping
    public ResponseEntity<Plate> update(@RequestBody Plate plate) {
        //comprobaciones
        this.plateService.save(plate);
        return ResponseEntity.status(HttpStatus.CREATED).body(plate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        this.plateService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
