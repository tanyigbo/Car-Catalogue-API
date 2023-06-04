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
    private String name;

    @Column
    private String make;

    @Column
    private String model;

    @Column
    private Integer view_count = 0;

    @Column
    @ManyToOne
    @JoinColumn(name = "manufacture_id")
    private Manufacture manufacture;

    @Column
    @OneToMany(mappedBy = "car")
    private List<Image> imageList;

    @Column
    @OneToMany (mappedBy = "car")
    private List<Review> reviewList;

    public Car() {
    }

    public Car(Long id, String name, String make, String model, Manufacture manufacture) {
        this.id = id;
        this.name = name;
        this.make = make;
        this.model = model;
        this.manufacture = manufacture;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
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

    public Manufacture getManufacture() {
        return manufacture;
    }

    public void setManufacture(Manufacture manufacture) {
        this.manufacture = manufacture;
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
                ", name='" + name + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", view_count=" + view_count +
                ", manufacture=" + manufacture +
                ", imageList=" + imageList +
                ", reviewList=" + reviewList +
                '}';
    }
}