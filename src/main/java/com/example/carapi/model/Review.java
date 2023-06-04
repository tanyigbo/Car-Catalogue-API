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

    public Review() {
    }
}
