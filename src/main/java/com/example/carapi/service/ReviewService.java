package com.example.carapi.service;

import com.example.carapi.exception.InformationNotFoundException;
import com.example.carapi.model.Car;
import com.example.carapi.model.Review;
import com.example.carapi.model.ReviewImage;
import com.example.carapi.repository.ReviewImageRepository;
import com.example.carapi.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final CarService carService;
    private final ReviewImageRepository reviewImageRepository;


    @Autowired
    public ReviewService(ReviewRepository reviewRepository, CarService carService,
                         ReviewImageRepository reviewImageRepository) {
        this.reviewRepository = reviewRepository;
        this.carService = carService;
        this.reviewImageRepository = reviewImageRepository;
    }

    /**
     * Calls Review Repository to find Review by id
     * @param reviewId id of requested Review
     * @return Review with matching id
     */
    public Review getReviewById(Long reviewId) {
        Optional<Review> review = reviewRepository.findById(reviewId);
        if (review.isPresent()) {
            return review.get();
        }
        throw new InformationNotFoundException("Review with ID " + reviewId + " was not found.");
    }

    /**
     * Calls Review Repository to add Review to Car
     * @param carId id of requested Car
     * @param reviewObj Review data
     * @return Newly added Review
     */
    public Review createReview(Long carId, Review reviewObj) {
        try {
            Car car = carService.getCarById(carId);
            reviewObj.setCar(car);
            return reviewRepository.save(reviewObj);
        } catch (Exception e) {
            throw new InformationNotFoundException(e.getMessage());
        }
    }

    /**
     * Calls ReviewImageRepository to add Review Image to Review
     * @param reviewId id of requested review
     * @param reviewImage Review Image data
     * @return Newly added Review Image
     */
    public Review addImageToReview(Long reviewId, ReviewImage reviewImage){
        try {
            Review review = getReviewById(reviewId);
            reviewImage.setReview(review);
            reviewImageRepository.save(reviewImage);
            return review;
        }catch (Exception e){
            throw new InformationNotFoundException(e.getMessage());
        }
    }

}
