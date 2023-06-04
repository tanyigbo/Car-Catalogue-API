package com.example.carapi.model;

import javax.persistence.*;


@Entity
@Table(name = "images")
public class Image {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String imageAddress;

    public Image() {
    }

}
