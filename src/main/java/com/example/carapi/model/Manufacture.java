package com.example.carapi.model;

import javax.persistence.*;

@Entity
@Table(name = "manufactures")
public class Manufacture {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    public Manufacture() {
    }

}
