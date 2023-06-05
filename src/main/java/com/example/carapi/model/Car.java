package com.example.carapi.model;

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
    private Integer view_count = 0;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @Column
    @OneToMany(mappedBy = "car")
    private List<Image> imageList;

    @Column
    @OneToMany (mappedBy = "car")
    private List<Review> reviewList;

    public Car() {
    }

    public Car(Long id, String model, Manufacturer manufacturer) {
        this.id = id;
        this.model = model;
        this.manufacturer = manufacturer;
    }

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getView_count() {
        return view_count;
    }

    public void setView_count(Integer view_count) {
        this.view_count = view_count;
    }

    public Manufacturer getManufacture() {
        return manufacturer;
    }

    public void setManufacture(Manufacturer manufacturer) {
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

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", view_count=" + view_count +
                ", manufacturer=" + manufacturer +
                ", reviewList=" + reviewList +
                '}';
    }
}