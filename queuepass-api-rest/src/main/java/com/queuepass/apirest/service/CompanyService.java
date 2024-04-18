package com.queuepass.apirest.service;

import com.queuepass.apirest.model.Company;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CompanyService {

    public Optional<Company> findById(Long id);

    Company save(Company company);

}
