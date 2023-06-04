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

    @Column
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    public Image() {
    }

    public Image(Long id, String imageAddress) {
        this.id = id;
        this.imageAddress = imageAddress;
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", imageAddress='" + imageAddress + '\'' +
                ", car=" + car +
                '}';
    }
}
