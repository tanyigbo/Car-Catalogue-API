package com.example.carapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car {


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String model;
    @Column
    private int range;
    @Column
    private float batteryCap;
    @Column
    private float cargoVol;
    @Column
    private float length;
    @Column
    private float width;
    @Column
    private float height;
    @Column
    private String driveType;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    @JsonIgnore
    private Manufacturer manufacturer;

    @Column
    @OneToMany(mappedBy = "car")
    private List<Image> imageList;

    @Column
    @OneToMany(mappedBy = "car")
    private List<Review> reviewList;

    public Car() {
    }

    public Car(String model, int range, float batteryCap, float cargoVol, float length,
               float width, float height, String driveType, Manufacturer manufacturer) {
        this.model = model;
        this.range = range;
        this.batteryCap = batteryCap;
        this.cargoVol = cargoVol;
        this.length = length;
        this.width = width;
        this.height = height;
        this.driveType = driveType;
        this.manufacturer = manufacturer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public float getBatteryCap() {
        return batteryCap;
    }

    public void setBatteryCap(float batteryCap) {
        this.batteryCap = batteryCap;
    }

    public float getCargoVol() {
        return cargoVol;
    }

    public void setCargoVol(float cargoVol) {
        this.cargoVol = cargoVol;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getDriveType() {
        return driveType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }
}