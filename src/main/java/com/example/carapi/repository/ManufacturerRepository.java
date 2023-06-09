package com.example.carapi.repository;

import com.example.carapi.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer,Long> {
    Manufacturer getManufacturerByName(String manufacturerName);
}
