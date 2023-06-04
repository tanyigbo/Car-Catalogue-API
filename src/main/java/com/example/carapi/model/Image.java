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
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @Column
    private String imageAddress;

    public Image() {
    }

    public Image(Long id, Car car, String imageAddress) {
        this.id = id;
        this.car = car;
        this.imageAddress = imageAddress;
    }

    public Long getId() {
        return id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", car=" + car +
                ", imageAddress='" + imageAddress + '\'' +
                '}';
    }
}
