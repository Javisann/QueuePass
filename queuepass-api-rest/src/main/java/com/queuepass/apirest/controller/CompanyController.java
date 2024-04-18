package com.queuepass.apirest.controller;

import com.queuepass.apirest.model.Company;
import com.queuepass.apirest.service.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/company")
public class CompanyController {


    @Autowired
    CompanyServiceImpl companyService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Company>> findById(@PathVariable Long id) {
        Optional<Company> company = this.companyService.findById(id);
        if (company.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(company);
        }
    }

    @PostMapping
    public ResponseEntity<Company> create(@RequestBody Company company) {
        //comprobaciones
        if (company.getId() != null) {
            return ResponseEntity.badRequest().build();
        } else {
            this.companyService.save(company);
            return ResponseEntity.status(HttpStatus.CREATED).body(company);
        }
    }

    @PutMapping
    public ResponseEntity<Company> update(@RequestBody Company company) {
        //comprobaciones
        this.companyService.save(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(company);
    }
}
