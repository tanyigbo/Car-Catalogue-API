package com.example.carapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "reviewImages")
public class ReviewImage {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String imageAddress;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    public ReviewImage() {
    }

    public ReviewImage(String imageAddress, Review review) {
        this.imageAddress = imageAddress;
        this.review = review;
    }

    public Long getId() {
        return id;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "ReviewImage{" +
                "id=" + id +
                ", imageAddress='" + imageAddress + '\'' +
                ", review=" + review +
                '}';
    }
}
