package com.queuepass.apirest.controller;

import com.queuepass.apirest.model.CompanyModel;
import com.queuepass.apirest.service.company.CompanyService;
import com.queuepass.apirest.service.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/api/company")
@CrossOrigin
@PreAuthorize("hasRole('ADMIN')")
public class CompanyController {


    @Autowired
    CompanyService companyService;

    @Autowired
    StorageService storageService;

    @GetMapping
    public ResponseEntity<Optional<CompanyModel>> find() {
        Optional<CompanyModel> company = this.companyService.find();
        if (company.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(company);
        }
    }

    //Método para guardar o actualizar
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CompanyModel> save(@RequestPart("new") CompanyModel company, @RequestPart(required = false) MultipartFile file) {
        String urlImage = null;

        if (file != null ) {
            if(!file.isEmpty()){
                String image = storageService.store(file);
                urlImage = MvcUriComponentsBuilder
                        .fromMethodName(FileUploadController.class, "serveFile", image)
                        .build().toUriString();
                company.setImage(urlImage);
            }
        }
        company =  this.companyService.save(company);
        return ResponseEntity.ok(company);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteById(){
        this.companyService.delete();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User deleted");
    }
}
