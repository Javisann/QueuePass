package com.queuepass.apirest.service.plate;

import com.queuepass.apirest.model.PlateModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public interface PlateService {

    public Optional<PlateModel> findById(Long id);

    public ArrayList<PlateModel> findByType(String type);

    public Optional<PlateModel> findByName(String name);

    PlateModel save(PlateModel plate);

    public void deleteById(Long id);
}
