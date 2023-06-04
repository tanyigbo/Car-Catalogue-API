package com.example.carapi.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String make;

    @Column
    private String model;

    @Column
    private Integer view_count = 0;

    public Car() {
    }
}