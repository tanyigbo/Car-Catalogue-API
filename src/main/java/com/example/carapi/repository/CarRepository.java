package com.example.carapi.repository;

import com.example.carapi.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    /**
     * SELECT * FROM cars WHERE car.model = model
     * @param model model name of car
     * @return Car object with matching model name
     */
    Car getCarByModel(String model);
}
