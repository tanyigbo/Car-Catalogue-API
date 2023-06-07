package com.example.carapi.controller;

import com.example.carapi.service.ReviewImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class ReviewImageController {

    private final ReviewImageService reviewImageService;

    @Autowired
    public ReviewImageController(ReviewImageService reviewImageService) {
        this.reviewImageService = reviewImageService;
    }


}
