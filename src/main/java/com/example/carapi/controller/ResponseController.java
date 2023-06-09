package com.example.carapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.HashMap;

@Controller
public class ResponseController {

    private static HashMap<String, Object> message;

    public ResponseController(){}

    public ResponseEntity<?> successfulRequestResponse(Object data, HttpStatus status) {
        message = new HashMap<>();
        message.put("message", "success");
        message.put("data", data);
        return new ResponseEntity<>(message, status);
    }

    public ResponseEntity<?> failureRequestResponse(String responseMessage, HttpStatus status) {
        message = new HashMap<>();
        message.put("message", responseMessage);
        return new ResponseEntity<>(message, status);
    }
}
