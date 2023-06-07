package com.example.carapi.service;

import com.example.carapi.exception.InformationNotFoundException;
import com.example.carapi.model.Review;
import com.example.carapi.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review getReviewById(Long reviewId){
        Optional<Review> review = reviewRepository.findById(reviewId);
        if(review.isPresent()){
            return review.get();
        }
        throw new InformationNotFoundException("Review with ID " + reviewId +" was not found.");
    }
}
