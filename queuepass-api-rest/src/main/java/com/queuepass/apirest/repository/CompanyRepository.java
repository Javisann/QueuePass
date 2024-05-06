package com.queuepass.apirest.repository;

import com.queuepass.apirest.model.CompanyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyModel, Long> {

}
