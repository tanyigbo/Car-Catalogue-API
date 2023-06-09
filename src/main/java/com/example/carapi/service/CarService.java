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

    /**
     * Calls Car Repository to find all Cars
     * @return List of all Cars
     */
    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    /**
     * Calls Cars Repository to find Car with provided id
     * @param carId id of requested Car
     * @return Car with matching id
     */
    public Car getCarById(Long carId){
        Optional<Car> car = carRepository.findById(carId);
        if(car.isPresent()){
            return car.get();
        }
        throw new InformationNotFoundException("Car with ID " + carId + " was not found.");
    }

    /**
     * Calls Car Repository to find Car with provided model name
     * @param modelName name of requested Car
     * @return Car with matching model name
     */
    public Car getCarByModel(String modelName){
        return carRepository.getCarByModel(modelName);
    }
}
