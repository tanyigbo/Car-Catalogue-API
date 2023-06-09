package com.example.carapi.controller;

import com.example.carapi.model.Manufacturer;
import com.example.carapi.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;
    private final ResponseController responseController;

    @Autowired
    public ManufacturerController(ManufacturerService manufacturerService, ResponseController responseController) {
        this.manufacturerService = manufacturerService;
        this.responseController = responseController;
    }

    // http://localhost:8080/api/manufacturers
    @GetMapping(path = "/manufacturers")
    public ResponseEntity<?> getAllManufacturers() {
        List<Manufacturer> manufacturers = manufacturerService.getAllManufacturers();
        return responseController.successfulRequestResponse(manufacturers, HttpStatus.OK);
    }

    @GetMapping(path = "/manufacturers/{manufacturerId}")
    private ResponseEntity<?> getManufacturerById(@PathVariable Long manufacturerId) {
        try {
            Manufacturer manufacturer = manufacturerService.getManufacturerById(manufacturerId);
            return responseController.successfulRequestResponse(manufacturer,HttpStatus.FOUND);
        } catch (Exception e) {
            return responseController.failureRequestResponse(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/manufacturers/{manufacturerName}/cars")
    public ResponseEntity<?> getAllCarsByManufacturerName(@PathVariable String manufacturerName) {
        Manufacturer manufacturer = manufacturerService.getManufacturerByName(manufacturerName);
        return responseController.successfulRequestResponse(manufacturer.getCarList(),HttpStatus.OK);
    }
}
