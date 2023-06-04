package com.example.carapi.model;

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

    public ReviewImage() {
    }
}
