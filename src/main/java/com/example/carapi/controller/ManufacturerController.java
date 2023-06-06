package com.example.carapi.controller;

import com.example.carapi.model.Manufacturer;
import com.example.carapi.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;
    private HashMap<String, Object> message;

    @Autowired
    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    private ResponseEntity<?> successfulRequestResponse(Object data, HttpStatus status) {
        message = new HashMap<>();
        message.put("message", "success");
        message.put("data", data);
        return new ResponseEntity<>(message, status);
    }

    private ResponseEntity<?> failureRequestResponse(String responseMessage, HttpStatus status) {
        message = new HashMap<>();
        message.put("message", responseMessage);
        return new ResponseEntity<>(message, status);
    }

    // http://localhost:8080/api/manufacturers
    @GetMapping(path = "/manufacturers")
    public ResponseEntity<?> getAllManufacturers() {
        List<Manufacturer> manufacturers = manufacturerService.getAllManufacturers();
        return successfulRequestResponse(manufacturers, HttpStatus.OK);
    }

    @GetMapping(path = "/manufacturers/{manufacturerId}")
    private ResponseEntity<?> getManufacturerById(@PathVariable Long manufacturerId) {
        try {
            Manufacturer manufacturer = manufacturerService.getManufacturerById(manufacturerId);
            return successfulRequestResponse(manufacturer,HttpStatus.FOUND);
        } catch (Exception e) {
            return failureRequestResponse(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/manufacturers/{manufacturerId}/cars")
    private ResponseEntity<?> getAllCarsByManufacturer(@PathVariable Long manufacturerId) {
        try {
            Manufacturer manufacturer = manufacturerService.getManufacturerById(manufacturerId);
            return successfulRequestResponse(manufacturer.getCarList(),HttpStatus.OK);
        }catch (Exception e){
            return failureRequestResponse(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
