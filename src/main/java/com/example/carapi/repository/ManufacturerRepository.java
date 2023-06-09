package com.example.carapi.repository;

import com.example.carapi.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer,Long> {
    /**
     * SELECT * FROM manufacturers WHERE manufacturer.name = manufacturerName
     * @param manufacturerName name of a manufacturer
     * @return Manufacturer object with matching name
     */
    Manufacturer getManufacturerByName(String manufacturerName);
}
