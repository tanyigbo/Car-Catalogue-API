package com.example.carapi.service;

import com.example.carapi.exception.InformationNotFoundException;
import com.example.carapi.model.Car;
import com.example.carapi.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    private CarService(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    public Car getCarById(Long car_id){
        Optional<Car> car = carRepository.findById(car_id);
        if(car.isPresent()){
            return car.get();
        }
        throw new InformationNotFoundException("Car with ID " + car_id + " was not found.");
    }
}
