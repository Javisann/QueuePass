package com.queuepass.apirest.repository;

import com.queuepass.apirest.model.Plate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface PlateRepository extends JpaRepository<Plate, Long> {

    public ArrayList<Plate> findByType(String type);

    public Optional<Plate> findByName(String name);

}
