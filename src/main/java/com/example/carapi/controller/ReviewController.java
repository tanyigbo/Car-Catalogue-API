package com.example.carapi.controller;

import com.example.carapi.model.Review;
import com.example.carapi.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class ReviewController {

    private final ReviewService reviewService;
    private final ResponseController responseController;

    @Autowired
    public ReviewController(ReviewService reviewService, ResponseController responseController) {
        this.reviewService = reviewService;
        this.responseController = responseController;
    }

    @GetMapping(path = "/reviews/{reviewId}/images")
    private ResponseEntity<?> getAllImagesOfReview(@PathVariable Long reviewId) {
        try {
            Review review = reviewService.getReviewById(reviewId);
            return responseController.successfulRequestResponse(review.getReviewImageList(), HttpStatus.OK);
        }catch (Exception e){
            return responseController.failureRequestResponse(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
