package com.example.carapi.service;

import com.example.carapi.exception.InformationNotFoundException;
import com.example.carapi.model.Manufacturer;
import com.example.carapi.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    @Autowired
    public ManufacturerService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    public Manufacturer getManufacturerById(Long manufacturerId) {
        Optional<Manufacturer> manufacturer = manufacturerRepository.findById(manufacturerId);
        if (manufacturer.isPresent()) {
            return manufacturer.get();
        }
        throw new InformationNotFoundException("Manufacturer with ID " + manufacturerId + " was not found.");
    }

}
