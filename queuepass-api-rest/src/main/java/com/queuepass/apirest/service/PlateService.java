package com.queuepass.apirest.service;

import com.queuepass.apirest.model.Plate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public interface PlateService {

    public Optional<Plate> findById(Long id);

    public ArrayList<Plate> findByType(String type);

    public Optional<Plate> findByName(String name);

    Plate save(Plate plate);

    public void deleteById(Long id);


}
