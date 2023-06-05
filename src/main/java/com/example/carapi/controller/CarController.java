package com.example.carapi.controller;

import com.example.carapi.model.Car;
import com.example.carapi.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class CarController {

    private static HashMap<String, Object> message;
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(path = "/cars")
    public ResponseEntity<?> getAllCars() {
        message = new HashMap<>();
        List<Car> cars = carService.getAllCars();
        message.put("message","success");
        message.put("data",cars);
        return new ResponseEntity<>(message, HttpStatus.FOUND);
    }

    @GetMapping(path = "/cars/{car_id}")
    public ResponseEntity<?> getCarById(@PathVariable Long car_id){
        message =new HashMap<>();
        try {
            Car car = carService.getCarById(car_id);
            message.put("message","success");
            message.put("data",car);
            return new ResponseEntity<>(message,HttpStatus.FOUND);
        }catch (Exception e){
            message.put("message","failure");
            message.put("data",e.getMessage());
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }

    }
}