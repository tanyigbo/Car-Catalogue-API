package com.example.carapi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String reviewText;

    @Column
    private String reviewerName;

    @Column
    private Integer viewCount = 0;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @Column
    @OneToMany(mappedBy = "review")
    private List<ReviewImage> reviewImageList;

    public Review() {
    }

    public Review(String title, String reviewText, String reviewerName,Car car) {
        this.title = title;
        this.reviewText = reviewText;
        this.reviewerName = reviewerName;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<ReviewImage> getReviewImageList() {
        return reviewImageList;
    }

    public void setReviewImageList(List<ReviewImage> reviewImageList) {
        this.reviewImageList = reviewImageList;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", reviewText='" + reviewText + '\'' +
                ", reviewerName='" + reviewerName + '\'' +
                ", viewCount=" + viewCount +
                '}';
    }
}

