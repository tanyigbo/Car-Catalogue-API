package com.example.carapi.controller;

import com.example.carapi.model.Manufacturer;
import com.example.carapi.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

    // http://localhost:8080/api/manufacturers
    @GetMapping(path = "/manufacturers")
    public ResponseEntity<?> getAllManufacturers() {
        message = new HashMap<>();
        List<Manufacturer> manufacturers = manufacturerService.getAllManufacturers();
        message.put("message","success");
        message.put("data",manufacturers);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
