package com.example.carapi.service;

import com.example.carapi.exception.InformationNotFoundException;
import com.example.carapi.model.Car;
import com.example.carapi.model.Review;
import com.example.carapi.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final CarService carService;


    @Autowired
    public ReviewService(ReviewRepository reviewRepository, CarService carService) {
        this.reviewRepository = reviewRepository;
        this.carService = carService;
    }

    public Review getReviewById(Long reviewId) {
        Optional<Review> review = reviewRepository.findById(reviewId);
        if (review.isPresent()) {
            return review.get();
        }
        throw new InformationNotFoundException("Review with ID " + reviewId + " was not found.");
    }

    public Review createReview(Long carId, Review reviewObj) {
        try {
            Car car = carService.getCarById(carId);
            reviewObj.setCar(car);
            return reviewRepository.save(reviewObj);
        } catch (Exception e) {
            throw new InformationNotFoundException("Car with ID " + carId + " was not found.");
        }
    }
}
