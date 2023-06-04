package com.example.carapi.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

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

    @Column
    @OneToMany(mappedBy = "manufacture")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Car> carList;

    public Manufacture() {
    }

    public Manufacture(String name, String country) {
        this.name = name;
        this.country = country;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> cars) {
        this.carList = cars;
    }

    @Override
    public String toString() {
        return "Manufacture{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", cars=" + carList +
                '}';
    }
}
