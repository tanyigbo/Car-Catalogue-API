package com.example.carapi.model;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String reviewText;

    @Column
    private String reviewerName;

    @Column
    private Integer viewCount = 0;

    @Column
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    public Review() {
    }

    public Review(Long id, String title, String reviewText, String reviewerName) {
        this.id = id;
        this.title = title;
        this.reviewText = reviewText;
        this.reviewerName = reviewerName;
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

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", reviewText='" + reviewText + '\'' +
                ", reviewerName='" + reviewerName + '\'' +
                ", viewCount=" + viewCount +
                ", car=" + car +
                '}';
    }
}
