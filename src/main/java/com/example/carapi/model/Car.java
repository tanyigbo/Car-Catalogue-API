package com.example.carapi.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
    @ManyToOne
    @JoinColumn(name = "manufacture_id")
    private Manufacture manufacture;

    @Column
    private String name;

    @Column
    private String make;

    @Column
    private String model;

    @Column
    private Integer view_count = 0;

    @Column
    @OneToMany(mappedBy = "car")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Image> imageList;

    public Car() {
    }

    public Car(Long id, Manufacture manufacture, String name, String make, String model) {
        this.id = id;
        this.manufacture = manufacture;
        this.name = name;
        this.make = make;
        this.model = model;
    }

    public Long getId() {
        return id;
    }

    public Manufacture getManufacture() {
        return manufacture;
    }

    public void setManufacture(Manufacture manufacture) {
        this.manufacture = manufacture;
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

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", manufacture=" + manufacture +
                ", name='" + name + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", view_count=" + view_count +
                ", imageList=" + imageList +
                '}';
    }
}
