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

    /**
     * GET Request for all Manufacturers
     * @return List of Manufacturers
     */
    // http://localhost:8080/api/manufacturers
    @GetMapping(path = "/manufacturers")
    public ResponseEntity<?> getAllManufacturers() {
        List<Manufacturer> manufacturers = manufacturerService.getAllManufacturers();
        return responseController.successfulRequestResponse(manufacturers, HttpStatus.OK);
    }

    /**
     * GET Request for Manufacturer with matching id
     * @param manufacturerId id of requested Manufacturer
     * @return Manufacturer with matching ID
     */
    // http://localhost:8080/api/manufacturers/1
    @GetMapping(path = "/manufacturers/{manufacturerId}")
    private ResponseEntity<?> getManufacturerById(@PathVariable Long manufacturerId) {
        try {
            Manufacturer manufacturer = manufacturerService.getManufacturerById(manufacturerId);
            return responseController.successfulRequestResponse(manufacturer,HttpStatus.FOUND);
        } catch (Exception e) {
            return responseController.failureRequestResponse(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    /**
     * GET Request for List of Cars by Manufacturer
     * @param manufacturerName name of Manufacturer
     * @return List of Cars with Manufacturer matching name
     */
    // http://localhost:8080/api/manufacturers/1/cars
    @GetMapping(path = "/manufacturers/{manufacturerName}/cars")
    public ResponseEntity<?> getAllCarsByManufacturerName(@PathVariable String manufacturerName) {
        Manufacturer manufacturer = manufacturerService.getManufacturerByName(manufacturerName);
        return responseController.successfulRequestResponse(manufacturer.getCarList(),HttpStatus.OK);
    }
}
