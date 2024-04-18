package com.queuepass.apirest.service;

import com.queuepass.apirest.model.Plate;
import com.queuepass.apirest.repository.PlateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PlateServiceImpl implements PlateService{

    @Autowired
    PlateRepository plateRepository;

    @Override
    public Optional<Plate> findById(Long id) {
        return this.plateRepository.findById(id);
    }

    @Override
    public ArrayList<Plate> findByType(String type) {
        return plateRepository.findByType(type);
    }

    @Override
    public Optional<Plate> findByName(String name) {
        return plateRepository.findByName(name);
    }

    @Override
    public Plate save(Plate plate) {
        return this.plateRepository.save(plate);
    }


    @Override
    public void deleteById(Long id) {
        this.plateRepository.deleteById(id);
    }
}
