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

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class CarController {

    private final CarService carService;
    private final ResponseController responseController;

    @Autowired
    public CarController(CarService carService, ResponseController responseController) {
        this.carService = carService;
        this.responseController = responseController;
    }

    @GetMapping(path = "/cars")
    public ResponseEntity<?> getAllCars() {
        List<Car> cars = carService.getAllCars();
        return responseController.successfulRequestResponse(cars,HttpStatus.OK);
    }

    @GetMapping(path = "/cars/{carId}")
    public ResponseEntity<?> getCarById(@PathVariable Long carId) {
        try {
            Car car = carService.getCarById(carId);
            return responseController.successfulRequestResponse(car,HttpStatus.FOUND);
        } catch (Exception e) {
            return responseController.failureRequestResponse(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/cars/model/{carModel}")
    public ResponseEntity<?> getCarByModel(@PathVariable String carModel) {
        try {
            Car car = carService.getCarByModel(carModel.replace("_"," "));
            return responseController.successfulRequestResponse(car,HttpStatus.OK);
        } catch (Exception e) {
            return responseController.failureRequestResponse(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/cars/{carId}/images")
    public ResponseEntity<?> getAllImagesOfCar(@PathVariable Long carId){
        try {
            Car car = carService.getCarById(carId);
            return responseController.successfulRequestResponse(car.getImageList(),HttpStatus.OK);
        }catch (Exception e){
            return responseController.failureRequestResponse(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/cars/{carId}/reviews")
    public ResponseEntity<?> getAllReviewsOfCar(@PathVariable Long carId){
        try{
            Car car = carService.getCarById(carId);
            return responseController.successfulRequestResponse(car.getReviewList(),HttpStatus.OK);
        }catch (Exception e){
            return responseController.failureRequestResponse(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
