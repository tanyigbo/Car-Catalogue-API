package com.example.carapi.repository;

import com.example.carapi.model.Car;
import com.example.carapi.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> findAllByManufacture(Manufacturer manufacturer);
}
