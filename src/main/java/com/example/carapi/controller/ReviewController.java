package com.example.carapi.controller;

import com.example.carapi.model.Review;
import com.example.carapi.model.ReviewImage;
import com.example.carapi.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * GET Request for Review by id
     * @param reviewId id of requested Review
     * @return Review with matching id
     */
    //http://localhost:8080/api/reviews/1
    @GetMapping(path = "reviews/{reviewId}")
    public ResponseEntity<?> getReviewById(@PathVariable Long reviewId){
        try {
            Review review = reviewService.getReviewById(reviewId);
            return responseController.successfulRequestResponse(review,HttpStatus.OK);
        }catch (Exception e){
            return responseController.failureRequestResponse(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    /**
     * GET Request for List of Review Images for Review by id
     * @param reviewId id of requested Review
     * @return List of Review Images
     */
    //http://localhost:8080/api/reviews/1/images
    @GetMapping(path = "/reviews/{reviewId}/images")
    public ResponseEntity<?> getAllImagesOfReview(@PathVariable Long reviewId) {
        try {
            Review review = reviewService.getReviewById(reviewId);
            return responseController.successfulRequestResponse(review.getReviewImageList(), HttpStatus.OK);
        } catch (Exception e) {
            return responseController.failureRequestResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * POST Request to add a new Review
     * @param carId id of Car being reviewed
     * @param reviewObj Review data
     * @return Newly created Review
     */
    //http://localhost:8080/api/reviews/new/1
    @PostMapping(path = "/reviews/new/{carId}")
    public ResponseEntity<?> addNewReview(@PathVariable Long carId, @RequestBody Review reviewObj) {
        try {
            Review review = reviewService.createReview(carId, reviewObj);
            return responseController.successfulRequestResponse(review, HttpStatus.CREATED);
        } catch (Exception e) {
            return responseController.failureRequestResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * POST Request to add a Review Image to a Review
     * @param reviewId id of requested Review
     * @param reviewImage Review Image data
     * @return Newly created Review Image
     */
    //http://localhost:8080/api/reviews/newImage/1
    @PostMapping(path = "/reviews/newImage/{reviewId}")
    public ResponseEntity<?> addNewReviewImageToReview(@PathVariable Long reviewId, @RequestBody ReviewImage reviewImage){
        try {
            Review review = reviewService.addImageToReview(reviewId,reviewImage);
            return responseController.successfulRequestResponse(review.getReviewImageList(),HttpStatus.CREATED);
        }catch (Exception e){
            return responseController.failureRequestResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
