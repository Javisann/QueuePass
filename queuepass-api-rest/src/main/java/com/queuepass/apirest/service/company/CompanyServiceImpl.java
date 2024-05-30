package com.queuepass.apirest.service.company;

import com.queuepass.apirest.model.CompanyModel;
import com.queuepass.apirest.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public Optional<CompanyModel> find() {
        return this.companyRepository.findAll().stream().findFirst();
    }

    @Override
    public CompanyModel save(CompanyModel company) {
        company.setId(1L);
        this.companyRepository.save(company);
        return company;
    }

    @Override
    public void delete() {
        this.companyRepository.deleteAll();
    }
}
