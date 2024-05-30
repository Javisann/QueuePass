package com.queuepass.apirest.repository;

import com.queuepass.apirest.model.PlateModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface PlateRepository extends JpaRepository<PlateModel, Long> {

    public ArrayList<PlateModel> findByType(String type);

    public Optional<PlateModel> findByName(String name);
}
