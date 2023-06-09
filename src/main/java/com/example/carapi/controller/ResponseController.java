package com.example.carapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.HashMap;

@Controller
public class ResponseController {

    private static HashMap<String, Object> message;

    public ResponseController(){}

    /**
     * Response for successful Request
     * @param data Data to be return as JSON
     * @param status HTTP status code of completed request
     * @return Response Entity with data and status code
     */
    public ResponseEntity<?> successfulRequestResponse(Object data, HttpStatus status) {
        message = new HashMap<>();
        message.put("message", "success");
        message.put("data", data);
        return new ResponseEntity<>(message, status);
    }

    /**
     * Response for failed Request
     * @param responseMessage Error message of exception
     * @param status HTTP status code of failed request
     * @return Response Entity with an error message and status code
     */
    public ResponseEntity<?> failureRequestResponse(String responseMessage, HttpStatus status) {
        message = new HashMap<>();
        message.put("message", responseMessage);
        return new ResponseEntity<>(message, status);
    }
}
