package com.queuepass.apirest.controller;

import com.queuepass.apirest.error.ApiError;
import com.queuepass.apirest.model.PlateModel;
import com.queuepass.apirest.service.plate.PlateServiceImpl;
import com.queuepass.apirest.service.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/plate")
@CrossOrigin
public class PlateController {

    @Autowired
    PlateServiceImpl plateService;

    @Autowired
    StorageService storageService;

    @PreAuthorize("hasRole('ADMIN')")
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
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> create(@RequestPart("new") PlateModel plate, @RequestPart(required = false) MultipartFile file) {
        if(plate.getId() != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError(HttpStatus.BAD_REQUEST, "You cant create a plate with assigned ID"));
        }

        if(plateService.findByName(plate.getName()).isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiError(HttpStatus.CONFLICT, "The plate with name : '"+ plate.getName() + "' is already exists"));
        }
        String urlImage = null;

        if (file != null ) {
            if(!file.isEmpty()){
                String image = storageService.store(file);
                urlImage = MvcUriComponentsBuilder
                        .fromMethodName(FileUploadController.class, "serveFile", image)
                        .build().toUriString();
                plate.setImage(urlImage);
            }
        }
        plate =  this.plateService.save(plate);
        return ResponseEntity.ok(plate);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> update(@RequestPart("new") PlateModel plate, @RequestPart(required = false) MultipartFile file) {
        if(plate.getId() == null){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError(HttpStatus.BAD_REQUEST, "To change properties to the plate, you must specify an ID"));
        }

        String urlImage = null;

        if (file != null ) {
            if(!file.isEmpty()){
                String image = storageService.store(file);
                urlImage = MvcUriComponentsBuilder
                        .fromMethodName(FileUploadController.class, "serveFile", image)
                        .build().toUriString();
                plate.setImage(urlImage);
            }
        }
        plate =  this.plateService.save(plate);
        return ResponseEntity.ok(plate);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        this.plateService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
