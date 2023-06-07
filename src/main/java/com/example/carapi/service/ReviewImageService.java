package com.example.carapi.service;

import com.example.carapi.repository.ReviewImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewImageService {

    private final ReviewImageRepository reviewImageRepository;

    @Autowired
    public ReviewImageService(ReviewImageRepository reviewImageRepository) {
        this.reviewImageRepository = reviewImageRepository;
    }


}
